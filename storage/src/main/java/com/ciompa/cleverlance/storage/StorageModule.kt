package com.ciompa.cleverlance.storage

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val storageModule = module {
    single {
        Room.inMemoryDatabaseBuilder(
            androidContext(),
            Storage::class.java
        ).build()
    }
}
