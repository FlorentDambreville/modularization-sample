package com.backmarket.entity

import java.util.Date

data class Diagnosis(
    val modelName: String,
    val screenTouchTest: ScreenTouchTest,
    val startTime: Date,
    val endTime: Date
) {

    data class ScreenTouchTest(
        val nbColumns: Int,
        val nbRows: Int,
        val nbCellsFilled: Int
    )
}