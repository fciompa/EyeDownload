package com.ciompa.cleverlance

import com.ciompa.cleverlance.common.ConnectivityMonitor
import com.ciompa.cleverlance.ui.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<ConnectivityMonitor> {
        ConnectivityMonitorImp(get())
    }

    viewModel<MainViewModel> {
        MainViewModel(get())
    }
}