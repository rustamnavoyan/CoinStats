package com.rustamnavoyan.coinstats.di

import com.rustamnavoyan.coinstats.data.mapper.CoinDataToRealmModelMapper
import com.rustamnavoyan.coinstats.data.network.CoinApiClient
import com.rustamnavoyan.coinstats.data.network.CoinService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.Realm
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Reusable
    fun providesCoinService(coinApiClient: CoinApiClient): CoinService =
        coinApiClient.getCoinService()

    @Provides
    @Singleton
    fun providesCoinsApiClient(): CoinApiClient = CoinApiClient()

    @Provides
    @Reusable
    fun providesCoinDataToRealmModelMapper() = CoinDataToRealmModelMapper()

    @Provides
    fun providesRealm() = Realm.getDefaultInstance()
}
