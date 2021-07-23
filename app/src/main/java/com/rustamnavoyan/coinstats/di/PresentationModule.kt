package com.rustamnavoyan.coinstats.di

import android.content.Context
import androidx.work.WorkManager
import com.rustamnavoyan.coinstats.viewmodel.mapper.CoinRealmResultsToDataModelMapper
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PresentationModule {

    @Provides
    @Reusable
    fun providesCoinRealmResultsToDataModelMapper() = CoinRealmResultsToDataModelMapper()

    @Provides
    fun providesContext(@ApplicationContext context: Context) = context

    @Provides
    @Singleton
    fun providesWorkManager(context: Context) = WorkManager.getInstance(context)
}
