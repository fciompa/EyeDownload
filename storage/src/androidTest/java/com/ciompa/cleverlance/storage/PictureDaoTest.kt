package com.ciompa.cleverlance.storage

import androidx.test.runner.AndroidJUnit4
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
class PictureDaoTest : AutoCloseKoinTest() {

    private val storage by inject<Storage>()

    @Before
    fun before() {
        startKoin {
            modules(storageModule)
        }
    }

    @Test
    fun init() = runBlocking {
        Assert.assertNull(storage.dao.picture())
    }

    @Test
    fun insert() = runBlocking {
        Assert.assertNull(storage.dao.picture())
        storage.dao.insertPicture(PictureEntity(0, PICTURE))
        Assert.assertEquals(PICTURE, storage.dao.picture().value)
    }

    @Test
    fun delete() = runBlocking {
        Assert.assertNull(storage.dao.picture())
        storage.dao.insertPicture(PictureEntity(0, PICTURE))
        Assert.assertEquals(PICTURE, storage.dao.picture().value)

        storage.dao.deletePicture()
        Assert.assertNull(storage.dao.picture())
    }
}