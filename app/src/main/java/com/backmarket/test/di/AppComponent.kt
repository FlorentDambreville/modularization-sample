package com.backmarket.test.di

import com.backmarket.core.di.BaseApplicationComponent
import com.backmarket.core.di.CoreComponent
import com.backmarket.core.di.FeatureScope
import com.backmarket.test.BackMarketTestApplication
import dagger.Component

@Component(
    modules = [AppModule::class],
    dependencies = [CoreComponent::class]
)
@FeatureScope
interface AppComponent :
    BaseApplicationComponent<BackMarketTestApplication> {

    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        fun coreComponent(coreComponent: CoreComponent): Builder
        fun appModule(appModule: AppModule): Builder
    }
}