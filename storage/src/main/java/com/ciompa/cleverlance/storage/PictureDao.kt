package com.ciompa.cleverlance.storage

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

interface PictureDao {
    @Query("SELECT * FROM picture")
    suspend fun picture(): PictureEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPicture(entity: PictureEntity)

    @Query("DELETE FROM picture")
    suspend fun deletePicture()

}