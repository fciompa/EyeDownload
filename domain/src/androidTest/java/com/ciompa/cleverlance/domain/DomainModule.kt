package com.ciompa.cleverlance.domain

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import com.ciompa.cleverlance.repository.RepositoryImp
import com.ciompa.cleverlance.storage.Storage
import com.ciompa.cleverlance.webservice.WebServiceImp
import org.koin.dsl.module

val domainModule = module {
    single {
        DomainImp(
            RepositoryImp(
                ConnectivityMonitorImp(),
                WebServiceImp(),
                Room.inMemoryDatabaseBuilder(
                    InstrumentationRegistry.getTargetContext(),
                    Storage::class.java
                ).build()
            )
        )
    }
}