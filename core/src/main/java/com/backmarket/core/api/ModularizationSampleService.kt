package com.backmarket.core.api

import com.backmarket.core.api.model.RemoteDiagnosis
import com.backmarket.core.util.Result

internal interface ModularizationSampleService {

    suspend fun postDiagnosis(remoteDiagnosis: RemoteDiagnosis) : Result<Unit>
}