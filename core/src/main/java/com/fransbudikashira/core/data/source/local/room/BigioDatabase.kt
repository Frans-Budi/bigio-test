package com.fransbudikashira.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fransbudikashira.core.data.source.local.entity.CharacterEntity
import com.fransbudikashira.core.data.source.local.entity.EpisodeEntity
import com.fransbudikashira.core.data.source.local.entity.LocationEntity

@Database(
    entities = [CharacterEntity::class, EpisodeEntity::class, LocationEntity::class],
    version = 1,
    exportSchema = false
)
abstract class BigioDatabase: RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    abstract fun episodeDao(): EpisodeDao

    abstract fun locationDao(): LocationDao
}