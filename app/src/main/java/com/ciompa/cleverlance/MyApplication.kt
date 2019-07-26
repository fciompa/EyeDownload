package com.ciompa.cleverlance

import android.app.Application
import com.ciompa.cleverlance.domain.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // start Koin!
        startKoin {
            androidContext(this@MyApplication)
            modules(listOf(domainModule))
        }
    }
}