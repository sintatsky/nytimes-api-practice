package com.sintatsky.astest.presentation

import android.app.Application
import com.sintatsky.astest.di.component.DaggerAppComponent

class ReviewApp : Application() {

    val component by lazy {
        DaggerAppComponent.factory().create(this)
    }
}