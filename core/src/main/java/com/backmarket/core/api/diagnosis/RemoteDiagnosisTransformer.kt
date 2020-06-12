package com.backmarket.core.api.diagnosis

import com.backmarket.core.api.model.RemoteDiagnosis
import com.backmarket.entity.Diagnosis
import javax.inject.Inject

internal class RemoteDiagnosisTransformer @Inject constructor() {

    fun transform(diagnosis: Diagnosis): RemoteDiagnosis {
        return RemoteDiagnosis(
            diagnosisInfos = RemoteDiagnosis.DiagnosisInfos(modelName = diagnosis.modelName),
            screenTouchTest = RemoteDiagnosis.ScreenShotTest(
                nbColumns = diagnosis.screenTouchTest.nbColumns,
                nbRows = diagnosis.screenTouchTest.nbRows,
                nbCellsFilled = diagnosis.screenTouchTest.nbCellsFilled
            ),
            startTime = diagnosis.startTime,
            endTime = diagnosis.endTime
        )
    }

}
