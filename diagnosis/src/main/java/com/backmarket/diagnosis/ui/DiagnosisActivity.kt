package com.backmarket.diagnosis.ui

import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.backmarket.diagnosis.R
import com.backmarket.diagnosis.di.DaggerDiagnosisComponent
import com.backmarket.diagnosis.di.DiagnosisActivityModule
import com.backmarket.test.BackMarketTestApplication
import kotlinx.android.synthetic.main.activity_diagnosis.*
import javax.inject.Inject


class DiagnosisActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: DiagnosisViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diagnosis)

        DaggerDiagnosisComponent.builder()
            .coreComponent(BackMarketTestApplication.coreComponent(this))
            .diagnosisActivityModule(
                DiagnosisActivityModule(
                    this
                )
            )
            .build()
            .inject(this)

        fillScreenWithSquares()
        showTestInstructionsDialog()

        viewModel.loading.observe(this, Observer { showLoading ->
            // I know ProgressDialog is deprecated (see alternatives here https://developer.android.com/guide/topics/ui/dialogs)
            // because it prevents users from interacting with the app. I decided to use it anyway, since there are no more
            // interactions left to do at this point on the screen.
            val dialog = ProgressDialog(this)
            dialog.setCancelable(false)
            dialog.setMessage(getString(R.string.loading_message))

            if (showLoading) {
                dialog.show()
            } else {
                if (dialog.isShowing) {
                    dialog.dismiss()
                }
            }
        })

        viewModel.diagnosisFinished.observe(this, Observer {
            Toast.makeText(this, getString(R.string.diagnosis_success), Toast.LENGTH_LONG).show()
            finish()
        })
    }

    private fun showTestInstructionsDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)

        builder
            .setTitle(R.string.touch_test_title)
            .setMessage(R.string.touch_test_message)
            .setPositiveButton(R.string.i_understand) { dialog, i ->
                dialog.dismiss()
            }

        val dialog = builder.create()
        dialog.show()
    }

    private fun fillScreenWithSquares() {
        val tableLayout =
            DispatchAllEventTableLayout(this)
        tableLayout.id = R.id.table_layout_id
        tableLayout.layoutParams = TableLayout.LayoutParams(
            TableLayout.LayoutParams.MATCH_PARENT,
            TableLayout.LayoutParams.MATCH_PARENT
        )

        val nbColumns = viewModel.getNbOfColumnInScreen(resources)
        val cellWidth = viewModel.getCellWidth(resources)

        val nbRow = viewModel.getRowNumber(resources)
        val cellHeight = viewModel.getCellHeight(resources)

        viewModel.getTotalCellNumber()

        for (rowNumber in 0 until nbRow) {
            val tableRow = TableRow(this)

            for (columnNumber in 0 until nbColumns) {
                val view = View(this)
                view.setBackgroundColor(resources.getColor(R.color.grey))
                view.setOnTouchListener { v, _ ->
                    if (!viewModel.alreadyTouchThisView(v.toString())) {
                        viewModel.viewTouched(v.toString())
                        v.setBackgroundColor(ContextCompat.getColor(this, com.backmarket.core.R.color.green))
                    }
                    true
                }
                tableRow.addView(view, TableRow.LayoutParams(cellWidth, cellHeight))
            }

            // Add vertical divider
            tableRow.showDividers = TableLayout.SHOW_DIVIDER_MIDDLE
            tableRow.dividerDrawable = ContextCompat.getDrawable(this, R.drawable.divider)

            // Add horizontal divider
            tableRow.setBackgroundColor(ContextCompat.getColor(this, com.backmarket.core.R.color.black))
            tableRow.setPadding(0, 4, 0, 4)

            tableLayout.addView(
                tableRow,
                TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    cellHeight
                )
            )
        }

        diagnosis_container.addView(tableLayout)
    }
}
