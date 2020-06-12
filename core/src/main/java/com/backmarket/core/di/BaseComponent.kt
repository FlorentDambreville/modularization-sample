package com.backmarket.core.di

import android.app.Activity
import android.app.Application

interface BaseComponent<T> {

    fun inject(target: T)
}

/**
 * Base dagger component for use in application.
 */
interface BaseApplicationComponent<T : Application> :
    BaseComponent<T>

/**
 * Base dagger component for use in activities.
 */
interface BaseActivityComponent<T : Activity> :
    BaseComponent<T>
