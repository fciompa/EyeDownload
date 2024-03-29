package com.ciompa.cleverlance.storage

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "property")
class PropertyEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "name", index = true) val name: String = "",
    @ColumnInfo(name = "value") var value: String = ""
)