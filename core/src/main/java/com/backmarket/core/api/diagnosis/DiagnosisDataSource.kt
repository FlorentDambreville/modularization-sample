package com.backmarket.core.api.diagnosis

import com.backmarket.core.util.Result
import com.backmarket.entity.Diagnosis

interface DiagnosisDataSource {

    suspend fun postDiagnosis(diagnosis: Diagnosis): Result<Unit>
}