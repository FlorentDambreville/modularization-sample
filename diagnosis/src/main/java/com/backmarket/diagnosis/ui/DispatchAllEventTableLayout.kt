package com.backmarket.diagnosis.ui

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.TableLayout

class DispatchAllEventTableLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : TableLayout(context, attrs) {

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        /*
         * This method JUST determines whether we want to intercept the motion.
         * If we return true, onTouchEvent will be called.
         */
        return false
    }
}