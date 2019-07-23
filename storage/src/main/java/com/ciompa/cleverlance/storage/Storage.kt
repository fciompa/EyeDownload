package com.ciompa.cleverlance.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Volatile
private lateinit var INSTANCE: Storage

@Database(entities = [PictureEntity::class, PropertyEntity::class], version = 1, exportSchema = false)
abstract class Storage : RoomDatabase() {
    abstract val dao: StorageDao

    companion object {
        fun getInstance(context: Context): Storage {
            synchronized(Storage::class) {
                if (!::INSTANCE.isInitialized) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        Storage::class.java,
                        "App.db"
                    ).build()
                }
            }

            return INSTANCE
        }
    }
}