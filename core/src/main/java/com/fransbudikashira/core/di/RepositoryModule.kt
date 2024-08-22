package com.fransbudikashira.core.di

import com.fransbudikashira.core.data.CharacterRepository
import com.fransbudikashira.core.domain.repository.ICharacterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(characterRepository: CharacterRepository): ICharacterRepository
}