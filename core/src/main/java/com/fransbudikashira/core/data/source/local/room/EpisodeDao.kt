package com.fransbudikashira.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fransbudikashira.core.data.source.local.entity.EpisodeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EpisodeDao {

    @Query("SELECT * FROM episode_table")
    fun getAllEpisode(): Flow<List<EpisodeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEpisode(episodes: EpisodeEntity)

    @Delete
    suspend fun deleteEpisode(character: EpisodeEntity)
}