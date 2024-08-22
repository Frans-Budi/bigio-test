package com.fransbudikashira.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fransbudikashira.core.data.source.local.entity.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character_table")
    fun getAllCharacter(): Flow<List<CharacterEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(characters: CharacterEntity)

    @Delete
    suspend fun deleteCharacter(character: CharacterEntity)
}