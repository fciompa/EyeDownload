package com.ciompa.cleverlance.domain

import androidx.test.runner.AndroidJUnit4
import com.ciompa.cleverlance.common.DownloadPictureError
import com.ciompa.cleverlance.common.PICTURE_SIZE
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject

@RunWith(AndroidJUnit4::class)
class DomainImpTest : AutoCloseKoinTest() {

    private val domain by inject<DomainImp>()

    @Before
    fun before() {
        startKoin {
            modules(domainModule)
        }
    }

    @Test
    fun downloadPictureInternetConnected() = runBlocking {

        val pictureError1 = domain.downloadPicture("ciompa", "frantisek")
        Assert.assertEquals(DownloadPictureError.Ok, pictureError1)

        val pictureError2 = domain.downloadPicture("ciompa", "frantisek")
        Assert.assertEquals(DownloadPictureError.NoInternet, pictureError2)
    }

    @Test
    fun downloadPictureUnauthorized() = runBlocking {

        val pictureError1 = domain.downloadPicture("Ciompa", "frantisek")
        Assert.assertEquals(DownloadPictureError.Unauthorized, pictureError1)

    }

    @Test
    fun downloadPictureOK() = runBlocking {

        val pictureError1 = domain.downloadPicture("ciompa", "frantisek")
        Assert.assertEquals(DownloadPictureError.Ok, pictureError1)
        val picture = domain.getPicture()
        Assert.assertEquals(PICTURE_SIZE, picture.size)
    }

    @Test
    fun downloadAndGetPictureNoInternet() = runBlocking {

        val pictureError = domain.downloadPicture("ciompa", "frantisek")
        Assert.assertEquals(DownloadPictureError.Ok, pictureError)
        val pictureError2 = domain.downloadPicture("ciompa", "frantisek")
        Assert.assertEquals(DownloadPictureError.NoInternet, pictureError2)
        val picture = domain.getPicture()
        Assert.assertEquals(PICTURE_SIZE, picture.size)

    }

    @Test
    fun downloadAndGetPictureUnauthorized() = runBlocking {

        val pictureError1 = domain.downloadPicture("ciompa", "frantisek")
        Assert.assertEquals(DownloadPictureError.Ok, pictureError1)
        val pictureError2 = domain.downloadPicture("ciompa", "frantisek")
        Assert.assertEquals(DownloadPictureError.NoInternet, pictureError2)
        val pictureError3 = domain.downloadPicture("Ciompa", "frantisek")
        Assert.assertEquals(DownloadPictureError.Unauthorized, pictureError3)
        val picture = domain.getPicture()
        Assert.assertEquals(0, picture.size)

    }

    @Test
    fun getPicture() = runBlocking {
        val picture = domain.getPicture()
        Assert.assertEquals(0, picture.size)
    }

    @Test
    fun getPicture2() = runBlocking {
        val pictureError1 = domain.downloadPicture("ciompa", "frantisek")
        Assert.assertEquals(DownloadPictureError.Ok, pictureError1)
        val picture = domain.getPicture()
        Assert.assertEquals(PICTURE_SIZE, picture.size)
    }

    @Test
    fun getUserLogin1() = runBlocking {
        val userLogin = domain.getUserLogin()
        Assert.assertEquals("", userLogin)
    }

    @Test
    fun getUserLogin2() = runBlocking {
        domain.downloadPicture("ciompa", "frantisek")
        Assert.assertEquals("ciompa", domain.getUserLogin())
    }

    @Test
    fun getUserLogin3() = runBlocking {
        domain.downloadPicture("ciompa", "frantisek")
        domain.downloadPicture("Ciompa", "frantisek")
        Assert.assertEquals("", domain.getUserLogin())
    }
}