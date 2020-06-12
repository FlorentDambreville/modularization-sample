package com.backmarket.navigation

/**
 * All addressable activities.
 *
 * Can contain intent extra names or functions associated with the activity creation.
 */
object Activities {

    private const val PACKAGE_NAME_PREFIX = "com.backmarket"

    object Login : AddressableActivity {
        override val className = "$PACKAGE_NAME_PREFIX.login.ui.LoginActivity"
    }

    object Diagnosis : AddressableActivity {
        override val className = "$PACKAGE_NAME_PREFIX.diagnosis.ui.DiagnosisActivity"
    }
}