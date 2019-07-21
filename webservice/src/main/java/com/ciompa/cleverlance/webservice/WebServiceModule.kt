package com.ciompa.cleverlance.webservice

import org.koin.dsl.module

val webServiceModule = module {
    single<WebService> { WebServiceImp()}
}
