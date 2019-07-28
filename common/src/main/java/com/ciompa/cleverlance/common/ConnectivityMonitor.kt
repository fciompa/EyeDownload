package com.ciompa.cleverlance.common

/**
 * Get state of internet connectivity
 */
interface ConnectivityMonitor {
    /**
     * @return true - there is internet connectivity, false there isn't internet connectivity
     */
    fun isConnected(): Boolean
}