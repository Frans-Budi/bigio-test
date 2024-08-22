package com.fransbudikashira.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fransbudikashira.core.data.source.local.entity.CharacterEntity
import com.fransbudikashira.core.data.source.local.entity.LocationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao {

    @Query("SELECT * FROM location_table")
    fun getAllLocation(): Flow<List<LocationEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(locations: LocationEntity)

    @Delete
    suspend fun deleteLocation(character: LocationEntity)
}