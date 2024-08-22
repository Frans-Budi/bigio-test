package com.fransbudikashira.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fransbudikashira.core.util.Constants.LOCATION_TABLE

@Entity(tableName = LOCATION_TABLE)
data class LocationEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("id")
    val id: String,

    @ColumnInfo("name")
    val name: String,

    @ColumnInfo("type")
    val type: String,

    @ColumnInfo("dimension")
    val dimension: String,
)