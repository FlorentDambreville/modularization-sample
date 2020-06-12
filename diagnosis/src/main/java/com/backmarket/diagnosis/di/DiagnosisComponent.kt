package com.backmarket.diagnosis.di

import com.backmarket.core.di.BaseActivityComponent
import com.backmarket.core.di.CoreComponent
import com.backmarket.core.di.FeatureScope
import com.backmarket.diagnosis.ui.DiagnosisActivity
import dagger.Component

@Component(
    modules = [DiagnosisActivityModule::class],
    dependencies = [CoreComponent::class]
)
@FeatureScope
interface DiagnosisComponent :
    BaseActivityComponent<DiagnosisActivity> {

    @Component.Builder
    interface Builder {
        fun build(): DiagnosisComponent
        fun coreComponent(coreComponent: CoreComponent): Builder
        fun diagnosisActivityModule(module: DiagnosisActivityModule): Builder
    }
}