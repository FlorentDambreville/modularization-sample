package com.backmarket.core.api.model

import java.util.Date

internal data class RemoteDiagnosis(
    val diagnosisInfos: DiagnosisInfos,
    val screenTouchTest: ScreenShotTest,
    val startTime: Date,
    val endTime: Date
) {
    data class DiagnosisInfos(
        val modelName: String
    )

    data class ScreenShotTest(
        val nbColumns: Int,
        val nbRows: Int,
        val nbCellsFilled: Int
    )
}