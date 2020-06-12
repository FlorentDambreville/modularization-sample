package com.backmarket.login.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.backmarket.login.usecase.LoginInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(
    private val loginInteractor: LoginInteractor
): ViewModel() {

    private val _loginSuccess = MutableLiveData<Unit>()
    val loginSuccess: LiveData<Unit>
        get() = _loginSuccess

    private val _loginError = MutableLiveData<Unit>()
    val loginError: LiveData<Unit>
        get() = _loginError

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    fun login(password: String) {
        _loading.value = true
        viewModelScope.launch(Dispatchers.Default) {
            val passwordIsValid = loginInteractor.validatePassword(password)
            if (passwordIsValid) {
                _loginSuccess.postValue(Unit)
            } else {
                _loginError.postValue(Unit)
            }
            _loading.postValue(false)
        }
    }

    fun validateEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}