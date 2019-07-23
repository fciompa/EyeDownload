package com.ciompa.cleverlance.storage

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject

class PropertyDaoTest : AutoCloseKoinTest() {

    private val PROPERTIES = listOf(
        PropertyEntity(0, "Property 02", "Value 02"),
        PropertyEntity(0, "Property 01", "Value 01")
    )

    private val storage by inject<Storage>()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun before() {
        startKoin {
            modules(storageModule)
        }
    }

    @Test
    fun init() {
        Assert.assertEquals(0, storage.dao.properties().getValueForTest()?.size)
    }

    @Test
    fun insert1() = runBlocking {
        Assert.assertEquals(0, storage.dao.properties().getValueForTest()?.size)
        storage.dao.insertProperty(PropertyEntity(0, "Property", "Value"))
        Assert.assertEquals(1, storage.dao.properties().getValueForTest()?.size)

    }

    @Test
    fun insert2() = runBlocking {
        storage.dao.insertProperties(PROPERTIES)
        Assert.assertEquals(2, storage.dao.properties().getValueForTest()?.size)

    }

    @Test
    fun load() = runBlocking {
        storage.dao.insertProperties(PROPERTIES)
        Assert.assertEquals("Property 01", storage.dao.properties().getValueForTest()?.get(0)?.name)
        Assert.assertEquals("Value 01", storage.dao.properties().getValueForTest()?.get(0)?.value)
        Assert.assertEquals(2, storage.dao.properties().getValueForTest()?.get(0)?.id)
    }

    @Test
    fun loadByName() = runBlocking {
        storage.dao.insertProperties(PROPERTIES)

        val property01 = storage.dao.property("Property 01")
        Assert.assertEquals("Property 01", property01?.name)
        Assert.assertEquals("Value 01", property01?.value)
        Assert.assertEquals(2, property01?.id)

        val property02 = storage.dao.property("Property 02")
        Assert.assertEquals("Property 02", property02?.name)
        Assert.assertEquals("Value 02", property02?.value)
        Assert.assertEquals(1, property02?.id)
    }

}