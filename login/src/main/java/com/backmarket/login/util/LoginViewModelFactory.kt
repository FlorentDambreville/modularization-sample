package com.backmarket.login.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.backmarket.login.ui.LoginViewModel
import com.backmarket.login.usecase.LoginInteractor

class LoginViewModelFactory(
    private val loginInteractor: LoginInteractor
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass != LoginViewModel::class.java) {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
        return LoginViewModel(loginInteractor) as T
    }
}