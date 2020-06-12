package com.backmarket.login.di

import androidx.lifecycle.ViewModelProvider
import com.backmarket.login.ui.LoginActivity
import com.backmarket.login.ui.LoginViewModel
import com.backmarket.login.usecase.LoginInteractor
import com.backmarket.login.util.LoginViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class LoginActivityModule(private val activity: LoginActivity) {

    @Provides
    fun provideAccountViewModel(
        factory: LoginViewModelFactory
    ): LoginViewModel =
        ViewModelProvider(activity, factory).get(LoginViewModel::class.java)

    @Provides
    fun provideAccountViewModelFactory(
        loginInteractor: LoginInteractor
    ): LoginViewModelFactory =
        LoginViewModelFactory(loginInteractor)

}
