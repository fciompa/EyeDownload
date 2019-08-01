package com.ciompa.cleverlance.repository

import com.ciompa.cleverlance.common.ConnectivityMonitor

/**
 * Get state of internet connectivity, each read change state to the opposite one
 */
class ConnectivityMonitorImp : ConnectivityMonitor {
    private var connected = true
    override fun isConnected(): Boolean = connected
    fun setConnected(connected: Boolean) {
        this.connected = connected
    }

}