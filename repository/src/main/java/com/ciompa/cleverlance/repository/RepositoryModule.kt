package com.ciompa.cleverlance.repository

//val repositoryModule = module {
//
//    single<WebService> { WebServiceImp() }
//        single {
//            Room.inMemoryDatabaseBuilder(
//                InstrumentationRegistry.getTargetContext(),
//                Storage::class.java
//            ).build()
//        }
//    single<Repository> { RepositoryImp(get(), get()) }
//}