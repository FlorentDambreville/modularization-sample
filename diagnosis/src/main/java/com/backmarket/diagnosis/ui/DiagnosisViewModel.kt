package com.backmarket.diagnosis.ui

import android.content.res.Resources
import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.backmarket.core.util.Result
import com.backmarket.core.util.dpToPx
import com.backmarket.diagnosis.usecase.data.DiagnosisRepository
import com.backmarket.entity.Diagnosis
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class DiagnosisViewModel(
    private val diagnosisRepository: DiagnosisRepository
): ViewModel() {

    private var neverTouchScreen = true
    private val touchedViewList = mutableListOf<String>()
    private var totalCellNumberInScreen: Int = 0
    private var columnNumber: Int = 0
    private var rowNumber: Int = 0

    private lateinit var startTime: Date

    private val _diagnosisFinished = MutableLiveData<Unit>()
    val diagnosisFinished: LiveData<Unit>
        get() = _diagnosisFinished

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading


    fun alreadyTouchThisView(id: String): Boolean {
        return touchedViewList.contains(id)
    }

    fun viewTouched(id: String) {
        startDiagnosisIfNotAlreadyStarted()
        touchedViewList.add(id)
        if (touchedViewList.size == totalCellNumberInScreen) {
            postDiagnosis(
                nbColumns = columnNumber,
                nbRows = rowNumber,
                nbCellsFilled = totalCellNumberInScreen
            )
        }
    }

    fun getNbOfColumnInScreen(resources: Resources): Int {
        val targetedCellSize = 48
        val width = getScreenWidth(resources)
        columnNumber = (width / resources.dpToPx(targetedCellSize)).toInt()
        return columnNumber
    }

    fun getCellWidth(resources: Resources): Int {
        return (getScreenWidth(resources) / columnNumber)
    }

    fun getRowNumber(resources: Resources): Int {
        rowNumber = getScreenHeight(resources) / getCellWidth(resources)
        return rowNumber
    }

    fun getCellHeight(resources: Resources): Int {
        return getScreenHeight(resources) / rowNumber
    }

    fun getTotalCellNumber() {
        totalCellNumberInScreen = columnNumber * rowNumber
    }

    private fun postDiagnosis(nbColumns: Int, nbRows: Int, nbCellsFilled: Int) {
        _loading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val diagnosis = Diagnosis(
                modelName = Build.MODEL,
                screenTouchTest = Diagnosis.ScreenTouchTest(
                    nbColumns = nbColumns,
                    nbRows = nbRows,
                    nbCellsFilled = nbCellsFilled
                ),
                startTime = startTime,
                endTime = Calendar.getInstance().time
            )

            val postDiagnosisResult = diagnosisRepository.postDiagnosis(diagnosis)

            withContext(Dispatchers.Main) {
                _loading.value = false
            }
            if (postDiagnosisResult is Result.Success) {
                withContext(Dispatchers.Main) {
                    _diagnosisFinished.value = Unit
                }
            }
        }
    }

    private fun startDiagnosisIfNotAlreadyStarted() {
        if (neverTouchScreen) {
            neverTouchScreen = false
            startDiagnosis()
        }
    }

    private fun startDiagnosis() {
        startTime = Calendar.getInstance().time
    }

    private fun getScreenWidth(resources: Resources): Int {
        return resources.displayMetrics.widthPixels
    }

    private fun getScreenHeight(resources: Resources): Int {
        return resources.displayMetrics.heightPixels
    }
}