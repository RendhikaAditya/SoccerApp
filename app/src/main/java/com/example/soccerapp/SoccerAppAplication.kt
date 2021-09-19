package com.example.soccerapp

import android.app.Application
import timber.log.Timber

class SoccerAppAplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}