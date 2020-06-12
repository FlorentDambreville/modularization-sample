package com.backmarket.navigation

import android.content.Intent
import android.os.Bundle

object Navigation {

    fun intentTo(addressableActivity: AddressableActivity, bundle: Bundle? = null): Intent {
        val intent = Intent(Intent.ACTION_VIEW).setClassName(BuildConfig.PACKAGE_NAME, addressableActivity.className)
        bundle?.let { intent.putExtras(it) }
        return intent
    }
}