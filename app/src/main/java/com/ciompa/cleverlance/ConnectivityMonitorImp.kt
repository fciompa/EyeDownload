package com.ciompa.cleverlance

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.ciompa.cleverlance.common.ConnectivityMonitor

class ConnectivityMonitorImp(context: Context) : ConnectivityMonitor {

    private val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private val activeNetwork: NetworkInfo? = cm.activeNetworkInfo

    override fun isConnected() = activeNetwork?.isConnected ?: false

}