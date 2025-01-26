package com.example.audiobook

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PodcastApplication : Application() {
    override fun onCreate() {
        super.onCreate()

    }
}