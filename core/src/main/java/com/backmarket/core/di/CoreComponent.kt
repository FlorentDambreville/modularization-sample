package com.backmarket.core.di

import android.content.Context
import com.backmarket.core.api.diagnosis.DiagnosisDataSource
import com.backmarket.core.api.di.CoreApiModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing application wide singletons.
 * To call this make use of BackMarketTestApplication.coreComponent or the
 * Activity.coreComponent extension function.
 */
@Component(
    modules = [CoreApiModule::class]
)
@Singleton
interface CoreComponent {

    @Component.Builder
    interface Builder {
        fun build(): CoreComponent
        @BindsInstance
        fun context(context: Context): Builder
    }

    fun provideDiagnosisDataSource() : DiagnosisDataSource

}