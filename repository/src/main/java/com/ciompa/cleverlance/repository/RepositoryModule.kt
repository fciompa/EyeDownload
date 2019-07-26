package com.ciompa.cleverlance.repository

import androidx.room.Room
import com.ciompa.cleverlance.common.ConnectivityMonitor
import com.ciompa.cleverlance.storage.Storage
import com.ciompa.cleverlance.webservice.WebService
import com.ciompa.cleverlance.webservice.WebServiceImp
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {

    single<WebService> {
        WebServiceImp()
    }

    single<Storage> {
        Room.inMemoryDatabaseBuilder(androidContext(), Storage::class.java).build()
    }

    single<ConnectivityMonitor> {
        ConnectivityMonitorImp()
    }

    single<Repository> {
        RepositoryImp(get())
    }
}
