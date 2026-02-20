package com.example.codexreview

import android.app.Application
import com.example.codexreview.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CodexReviewApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CodexReviewApp)
            modules(appModules)
        }
    }
}
