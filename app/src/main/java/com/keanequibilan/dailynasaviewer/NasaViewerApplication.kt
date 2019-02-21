package com.keanequibilan.dailynasaviewer

import android.app.Application
import com.keanequibilan.dailynasaviewer.di.APP_MODULE
import org.koin.android.ext.android.startKoin

@Suppress("unused")
class NasaViewerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(
            androidContext = this,
            modules = listOf(APP_MODULE)
        )
    }
}