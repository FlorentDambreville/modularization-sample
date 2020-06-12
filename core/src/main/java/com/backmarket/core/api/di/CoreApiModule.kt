package com.backmarket.core.api.di

import com.backmarket.core.api.ModularizationSampleService
import com.backmarket.core.api.ModularizationSampleServiceImpl
import com.backmarket.core.api.diagnosis.DiagnosisDataSource
import com.backmarket.core.api.diagnosis.DiagnosisRemoteDataSource
import com.backmarket.core.api.diagnosis.RemoteDiagnosisTransformer
import dagger.Module
import dagger.Provides

@Module
internal class CoreApiModule {

    @Provides
    fun provideService(
    ): ModularizationSampleService {
        return ModularizationSampleServiceImpl()
    }

    @Provides
    fun provideDiagnosisDataSource(
        service: ModularizationSampleService,
        transformer: RemoteDiagnosisTransformer
    ) : DiagnosisDataSource {
        return DiagnosisRemoteDataSource(service, transformer)
    }
}