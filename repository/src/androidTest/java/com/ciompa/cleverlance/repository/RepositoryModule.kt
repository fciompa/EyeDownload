package com.ciompa.cleverlance.repository

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import com.ciompa.cleverlance.storage.Storage
import com.ciompa.cleverlance.webservice.WebServiceImp
import org.koin.dsl.module

val repositoryModule = module {
    single {
        RepositoryImp(
            ConnectivityMonitorImp(),
            WebServiceImp(),
            Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getTargetContext(),
                Storage::class.java
            ).build()
        )
    }
}