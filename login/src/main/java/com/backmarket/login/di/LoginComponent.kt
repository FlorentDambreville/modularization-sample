package com.backmarket.login.di

import com.backmarket.core.di.BaseActivityComponent
import com.backmarket.core.di.CoreComponent
import com.backmarket.core.di.FeatureScope
import com.backmarket.login.ui.LoginActivity
import dagger.Component

@Component(
    modules = [LoginActivityModule::class],
    dependencies = [CoreComponent::class]
)
@FeatureScope
interface LoginComponent :
    BaseActivityComponent<LoginActivity> {

    @Component.Builder
    interface Builder {
        fun build(): LoginComponent
        fun coreComponent(coreComponent: CoreComponent): Builder
        fun loginActivityModule(module: LoginActivityModule): Builder
    }
}