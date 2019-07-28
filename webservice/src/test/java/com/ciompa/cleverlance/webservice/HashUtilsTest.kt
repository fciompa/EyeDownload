package com.ciompa.cleverlance.webservice

import com.ciompa.cleverlance.common.HashUtils
import org.junit.Assert
import org.junit.Test

class HashUtilsTest {

    @Test
    fun sha1Milan() {
        val sha1 = HashUtils.sha1("milan")
        Assert.assertEquals("c5983e484db0b621516387b3e50af84020b214c0".toUpperCase(), sha1)
    }

    @Test
    fun sha1Frantisek() {
        val sha1 = HashUtils.sha1("františek")
        Assert.assertEquals("8c1be6393ca5f8f06b70f2bc0172a5cb5bb26004".toUpperCase(), sha1)
    }
}