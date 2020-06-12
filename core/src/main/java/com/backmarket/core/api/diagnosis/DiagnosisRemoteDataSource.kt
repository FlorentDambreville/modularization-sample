package com.backmarket.core.api.diagnosis

import com.backmarket.core.api.ModularizationSampleService
import com.backmarket.core.util.Result
import com.backmarket.entity.Diagnosis

internal class DiagnosisRemoteDataSource(
    private val service: ModularizationSampleService,
    private val remoteDiagnosisTransformer: RemoteDiagnosisTransformer
) : DiagnosisDataSource {

    override suspend fun postDiagnosis(diagnosis: Diagnosis): Result<Unit> {
        val remoteDiagnosis = remoteDiagnosisTransformer.transform(diagnosis)
        return service.postDiagnosis(remoteDiagnosis)
    }
}