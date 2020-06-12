package com.backmarket.test

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.backmarket.core.di.CoreComponent
import com.backmarket.core.di.DaggerCoreComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import com.backmarket.test.di.AppModule
import com.backmarket.test.di.DaggerAppComponent
import kotlin.coroutines.CoroutineContext

class BackMarketTestApplication : Application(), CoroutineScope {

    private lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = job

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.builder()
            .context(this)
            .build()
    }


    companion object {
        @JvmStatic
        fun coreComponent(context: Context) =
            (context.applicationContext as BackMarketTestApplication).coreComponent
    }

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent
            .builder()
            .coreComponent(coreComponent)
            .appModule(AppModule(this))
            .build()
            .inject(this)
    }
}

fun Activity.coreComponent() = BackMarketTestApplication.coreComponent(this)
fun Fragment.coreComponent(context: Context) = BackMarketTestApplication.coreComponent(context)