package com.ciompa.cleverlance.storage

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import org.koin.dsl.module

val storageModule = module {
    single {
        Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getTargetContext(),
            Storage::class.java
        ).build()
    }
}
