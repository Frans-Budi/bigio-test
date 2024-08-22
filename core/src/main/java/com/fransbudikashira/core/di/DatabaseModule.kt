package com.fransbudikashira.core.di

import android.content.Context
import androidx.room.Room
import com.fransbudikashira.core.data.source.local.room.BigioDatabase
import com.fransbudikashira.core.data.source.local.room.CharacterDao
import com.fransbudikashira.core.data.source.local.room.EpisodeDao
import com.fransbudikashira.core.data.source.local.room.LocationDao
import com.fransbudikashira.core.util.Constants.BIGIO_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): BigioDatabase = Room.databaseBuilder(
        context,
        BigioDatabase::class.java,
        BIGIO_DATABASE
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideCharacterDao(database: BigioDatabase): CharacterDao = database.characterDao()

    @Provides
    fun provideEpisodeDao(database: BigioDatabase): EpisodeDao = database.episodeDao()

    @Provides
    fun provideLocationDao(database: BigioDatabase): LocationDao = database.locationDao()
}