package com.ciompa.cleverlance.repository

import com.ciompa.cleverlance.common.ConnectivityMonitor

/**
 * Get state of internet connetivity, each read change state to the opposite one
 */
class ConnectivityMonitorImp : ConnectivityMonitor {
    private var connected = false
    override fun isConnected(): Boolean {
        connected = !connected
        return connected
    }
}