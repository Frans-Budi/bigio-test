package com.fransbudikashira.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fransbudikashira.core.util.Constants.CHARACTER_TABLE
import com.fransbudikashira.core.util.StatusCharacter

@Entity(tableName = CHARACTER_TABLE)
data class CharacterEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("id")
    val id: String,

    @ColumnInfo("imageUrl")
    val imageUrl: String,

    @ColumnInfo("name")
    val name: String,

    @ColumnInfo("species")
    val species: String,

    @ColumnInfo("status")
    val status: String,
)