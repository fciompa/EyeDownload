package com.ciompa.cleverlance.di

import androidx.room.Room
import com.ciompa.cleverlance.domain.Domain
import com.ciompa.cleverlance.domain.DomainImp
import com.ciompa.cleverlance.repository.Repository
import com.ciompa.cleverlance.repository.RepositoryImp
import com.ciompa.cleverlance.storage.Storage
import com.ciompa.cleverlance.webservice.WebService
import com.ciompa.cleverlance.webservice.WebServiceImp
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val diModule = module {

    single<WebService> {
        WebServiceImp()
    }

    single<Storage> {
        Room.databaseBuilder(androidContext(), Storage::class.java, "app.db").build()
    }

    single<Repository> {
        RepositoryImp(get())
    }

    single<Domain> {
        DomainImp()
    }

}