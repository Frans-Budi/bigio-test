package com.fransbudikashira.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fransbudikashira.core.util.Constants.EPISODE_TABLE

@Entity(tableName = EPISODE_TABLE)
data class EpisodeEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("id")
    val id: String,

    @ColumnInfo("title")
    val title: String,

    @ColumnInfo("episode")
    val episode: String,

    @ColumnInfo("airDate")
    val airDate: String,
)