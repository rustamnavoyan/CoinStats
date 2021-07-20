package com.rustamnavoyan.coinstats.di

import com.rustamnavoyan.coinstats.ui.CoinDataToUiModelMapper
import com.rustamnavoyan.coinstats.ui.adapter.CoinAdapter
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object UiModule {

    @Provides
    @Reusable
    fun providesCoinDataToUiModelMapper() = CoinDataToUiModelMapper()

    @Provides
    @Reusable
    fun providesCoinAdapter() = CoinAdapter()
}
