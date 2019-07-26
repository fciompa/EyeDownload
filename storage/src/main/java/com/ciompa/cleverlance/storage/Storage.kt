package com.ciompa.cleverlance.storage

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PictureEntity::class, PropertyEntity::class], version = 1, exportSchema = false)
abstract class Storage : RoomDatabase() {
    abstract val dao: StorageDao
}