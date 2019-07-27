package com.ciompa.cleverlance

import android.app.Application
import com.ciompa.cleverlance.di.diModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // start Koin!
        startKoin {
            androidContext(this@MyApp)
            modules(listOf(appModule, diModule))
        }
    }
}