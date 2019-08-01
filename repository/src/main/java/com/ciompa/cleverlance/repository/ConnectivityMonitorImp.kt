package com.ciompa.cleverlance.repository

import com.ciompa.cleverlance.common.ConnectivityMonitor

/**
 * Implementation for testing only, default value is connected, for testing purpose is added connectivity setter
 */
class ConnectivityMonitorImp : ConnectivityMonitor {
    private var connected = true
    override fun isConnected(): Boolean = connected
    fun setConnected(connected: Boolean) {
        this.connected = connected
    }

}