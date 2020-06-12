package com.backmarket.diagnosis.di

import androidx.lifecycle.ViewModelProvider
import com.backmarket.core.api.diagnosis.DiagnosisDataSource
import com.backmarket.diagnosis.ui.DiagnosisActivity
import com.backmarket.diagnosis.ui.DiagnosisViewModel
import com.backmarket.diagnosis.usecase.data.DiagnosisRepository
import com.backmarket.diagnosis.util.DiagnosisViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class DiagnosisActivityModule(private val activity: DiagnosisActivity) {

    @Provides
    fun provideDiagnosisViewModel(
        factory: DiagnosisViewModelFactory
    ): DiagnosisViewModel =
        ViewModelProvider(activity, factory).get(DiagnosisViewModel::class.java)

    @Provides
    fun provideDiagnosisViewModelFactory(
        diagnosisRepository: DiagnosisRepository
    ): DiagnosisViewModelFactory =
        DiagnosisViewModelFactory(diagnosisRepository)

    @Provides
    fun provideDiagnosisRepository(
        diagnosisDataSource: DiagnosisDataSource
    ): DiagnosisRepository =
        DiagnosisRepository(diagnosisDataSource)

}
