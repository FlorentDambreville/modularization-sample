package com.backmarket.diagnosis.usecase.data

import com.backmarket.core.api.diagnosis.DiagnosisDataSource
import com.backmarket.core.util.Result
import com.backmarket.entity.Diagnosis

class DiagnosisRepository(
    private val diagnosisDataSource: DiagnosisDataSource
) {

    suspend fun postDiagnosis(diagnosis: Diagnosis): Result<Unit> {
        return diagnosisDataSource.postDiagnosis(diagnosis)
    }
}