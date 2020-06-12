package com.backmarket.core.util

import android.content.res.Resources
import android.util.TypedValue
import android.view.View
import androidx.annotation.Dimension

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun Resources.dpToPx(@Dimension(unit = Dimension.DP) dp: Int
): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp.toFloat(),
        this.displayMetrics
    )
}