package com.backmarket.login.ui

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import com.backmarket.core.util.hide
import com.backmarket.core.util.show
import com.backmarket.login.R
import com.backmarket.login.di.DaggerLoginComponent
import com.backmarket.login.di.LoginActivityModule
import com.backmarket.login.util.TextWatcherAdapter
import com.backmarket.navigation.Activities
import com.backmarket.navigation.Navigation.intentTo
import com.backmarket.test.BackMarketTestApplication.Companion.coreComponent
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

         DaggerLoginComponent.builder()
            .coreComponent(coreComponent(this))
            .loginActivityModule(LoginActivityModule(this))
            .build()
            .inject(this)

        viewModel.loading.observe(this, Observer {
            if (it) {
                progress_bar.show()
            } else {
                progress_bar.hide()
            }
        })

        viewModel.loginSuccess.observe(this, Observer {
            password.clearFocus()
            email.text?.clear()
            password.text?.clear()
            startActivity(intentTo(Activities.Diagnosis))
        })

        viewModel.loginError.observe(this, Observer {
            Snackbar.make(sign_in_container, getString(R.string.login_error_title), Snackbar.LENGTH_LONG).show()
        })

        email.addTextChangedListener(object : TextWatcherAdapter() {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (viewModel.validateEmail(email.text.toString())) {
                    email_text_input_layout.error = null
                }
            }
        })

        password.addTextChangedListener(object : TextWatcherAdapter() {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.isNotEmpty() == true) {
                    password_text_input_layout.error = null
                }
            }
        })

        password.setOnEditorActionListener { _, actionId, _ ->
            when(actionId) {
                EditorInfo.IME_ACTION_DONE ->  {
                    hideKeyboardFrom(this, sign_in_container)
                    sign_in_button.performClick()
                }
            }
            true
        }

        sign_in_button.setOnClickListener {
            when {
                !viewModel.validateEmail(email.text.toString()) ->
                    email_text_input_layout.error = getString(R.string.login_error_missingemail)
                password.text?.isEmpty() == true ->
                    password_text_input_layout.error = getString(R.string.login_error_missingpassword)
                else -> {
                    viewModel.login(
                        password.text.toString()
                    )
                }
            }
        }
    }

    private fun hideKeyboardFrom(context: Context?, view: View) {
        val imm = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
