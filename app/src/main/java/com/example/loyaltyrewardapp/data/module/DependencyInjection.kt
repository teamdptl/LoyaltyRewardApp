package com.example.loyaltyrewardapp.data.module

import com.example.loyaltyrewardapp.data.api.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DependencyInjection {

    @Singleton
    @Provides
    fun provideRetrofitService(): RetrofitClient {
        return RetrofitClient()
    }
}
