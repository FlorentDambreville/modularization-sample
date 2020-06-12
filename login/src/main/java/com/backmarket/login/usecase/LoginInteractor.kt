package com.backmarket.login.usecase

import javax.inject.Inject

class LoginInteractor @Inject constructor() {

    suspend fun validatePassword(password: String): Boolean {
        return password == "iamabackmaker"
    }
}