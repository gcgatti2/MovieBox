package com.backbase.assignment.ui

import android.app.Application
import com.backbase.assignment.ui.presentation.util.startNetworkCallback

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startNetworkCallback()
    }
}