package com.backmarket.login.util

import android.text.Editable
import android.text.TextWatcher

open class TextWatcherAdapter: TextWatcher {
    override fun afterTextChanged(s: Editable?) {
        // Do Nothing
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        // Do Nothing
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        // Do Nothing
    }
}