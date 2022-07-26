package com.github.welshk.ukko.di

import com.github.welshk.ukko.data.DataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Module needed for Hilt
 */
@Module
@InstallIn(SingletonComponent::class)
class DataRepositoryModule {
    @Provides
    fun provideDataRepository(): DataRepository {
        return DataRepository()
    }
}