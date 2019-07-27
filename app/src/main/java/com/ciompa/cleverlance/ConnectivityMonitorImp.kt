package com.ciompa.cleverlance

import android.content.Context
import android.net.ConnectivityManager
import com.ciompa.cleverlance.common.ConnectivityMonitor

class ConnectivityMonitorImp(context: Context) : ConnectivityMonitor {

    private val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun isConnected() = cm.activeNetworkInfo?.isConnected ?: false

}