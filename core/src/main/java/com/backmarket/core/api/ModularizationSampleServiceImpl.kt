package com.backmarket.core.api

import com.backmarket.core.api.model.RemoteDiagnosis
import com.backmarket.core.util.Result
import kotlinx.coroutines.delay

internal class ModularizationSampleServiceImpl: ModularizationSampleService {

    override suspend fun postDiagnosis(remoteDiagnosis: RemoteDiagnosis): Result<Unit> {
        delay(5000)
        return Result.Success(Unit)
    }
}