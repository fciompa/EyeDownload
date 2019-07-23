package com.ciompa.cleverlance.repository

import androidx.test.runner.AndroidJUnit4
import com.ciompa.cleverlance.common.HashUtils
import com.ciompa.cleverlance.common.PICTURE
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject

@RunWith(AndroidJUnit4::class)
class RepositoryImpTest : AutoCloseKoinTest() {

    private val repository by inject<RepositoryImp>()

    @Before
    fun before() {
        startKoin {
            modules(repositoryModule)
        }
    }

    @Test
    fun downloadPictureInternetConnected() = runBlocking {

        val pictureError1 = repository.downloadPicture("ciompa", HashUtils.sha1("frantisek"))
        Assert.assertEquals(DownloadPictureError.Ok, pictureError1)

        val pictureError2 = repository.downloadPicture("ciompa", HashUtils.sha1("frantisek"))
        Assert.assertEquals(DownloadPictureError.NoInternet, pictureError2)
    }

    @Test
    fun downloadPictureUnauthorized() = runBlocking {

        val pictureError1 = repository.downloadPicture("Ciompa", HashUtils.sha1("frantisek"))
        Assert.assertEquals(DownloadPictureError.Unauthorized, pictureError1)

    }

    @Test
    fun downloadPictureOK() = runBlocking {

        val pictureError1 = repository.downloadPicture("ciompa", HashUtils.sha1("frantisek"))
        Assert.assertEquals(DownloadPictureError.Ok, pictureError1)
        val picture = repository.getPicture()
        Assert.assertEquals(PICTURE, picture)
    }

    @Test
    fun downloadAndGetPictureNoInternet() = runBlocking {

        val pictureError = repository.downloadPicture("ciompa", HashUtils.sha1("frantisek"))
        Assert.assertEquals(DownloadPictureError.Ok, pictureError)
        val pictureError2 = repository.downloadPicture("ciompa", HashUtils.sha1("frantisek"))
        Assert.assertEquals(DownloadPictureError.NoInternet, pictureError2)
        val picture = repository.getPicture()
        Assert.assertEquals(PICTURE, picture)

    }

    @Test
    fun downloadAndGetPictureUnauthorized() = runBlocking {

        val pictureError1 = repository.downloadPicture("ciompa", HashUtils.sha1("frantisek"))
        Assert.assertEquals(DownloadPictureError.Ok, pictureError1)
        val pictureError2 = repository.downloadPicture("ciompa", HashUtils.sha1("frantisek"))
        Assert.assertEquals(DownloadPictureError.NoInternet, pictureError2)
        val pictureError3 = repository.downloadPicture("Ciompa", HashUtils.sha1("frantisek"))
        Assert.assertEquals(DownloadPictureError.Unauthorized, pictureError3)
        val picture = repository.getPicture()
        Assert.assertEquals("", picture)

    }

    @Test
    fun getPicture() = runBlocking {
        val picture = repository.getPicture()
        Assert.assertEquals("", picture)
    }

    @Test
    fun getPicture2() = runBlocking {
        repository.setPicture("ABC")
        Assert.assertEquals("ABC", repository.getPicture())
        repository.setPicture("123")
        Assert.assertEquals("123", repository.getPicture())
    }

    @Test
    fun getUserLogin1() = runBlocking {
        val userLogin = repository.getUserLogin()
        Assert.assertEquals("", userLogin)
    }

    @Test
    fun getUserLogin2() = runBlocking {
        repository.setUserLogin("ciompa")
        Assert.assertEquals("ciompa", repository.getUserLogin())
        repository.setUserLogin("frantisek")
        Assert.assertEquals("frantisek", repository.getUserLogin())
    }
}