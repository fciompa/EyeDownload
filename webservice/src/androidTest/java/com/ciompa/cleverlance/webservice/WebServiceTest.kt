package com.ciompa.cleverlance.webservice

import androidx.test.runner.AndroidJUnit4
import com.ciompa.cleverlance.common.HashUtils
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject


@RunWith(AndroidJUnit4::class)
class WebServiceTest: AutoCloseKoinTest() {

    private val webService by inject<WebService>()

    @Before
    fun before() {
        startKoin {
            modules(webServiceModule)
        }
    }

    @Test
    fun downloadCiompaOK() = runBlocking {

        val downloadImage = webService.downloadImage(
            "ciompa",
            HashUtils.sha1("frantisek")
        )

        Assert.assertNotNull(downloadImage)
        Assert.assertNotNull(downloadImage.imageEncoded)
        Assert.assertEquals(200, downloadImage.code)
        Assert.assertEquals("OK", downloadImage.message)
    }

    @Test
    fun downloadCiompaError() = runBlocking {

        val downloadImage = webService.downloadImage(
            "Ciompa",
            HashUtils.sha1("frantisek")
        )

        Assert.assertNotNull(downloadImage)
        Assert.assertNull(downloadImage.imageEncoded)
        Assert.assertEquals(401, downloadImage.code)
        Assert.assertEquals("Unauthorized", downloadImage.message)
    }
}

