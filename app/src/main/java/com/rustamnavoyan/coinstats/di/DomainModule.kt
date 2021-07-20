package com.rustamnavoyan.coinstats.di

import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Reusable
    fun providesCoroutineScope(): CoroutineScope = CoroutineScope(Dispatchers.Main)
}
