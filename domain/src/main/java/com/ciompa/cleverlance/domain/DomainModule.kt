package com.ciompa.cleverlance.domain

import org.koin.dsl.module

val domainModule = module {

    single<Domain> {
        DomainImp()
    }

}