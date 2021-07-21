package com.rustamnavoyan.coinstats.di

import com.rustamnavoyan.coinstats.viewmodel.mapper.CoinRealmResultsToDataModelMapper
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PresentationModule {

    @Provides
    @Reusable
    fun providesCoinRealmResultsToDataModelMapper() = CoinRealmResultsToDataModelMapper()
}
