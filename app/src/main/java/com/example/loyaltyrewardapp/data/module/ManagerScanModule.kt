package com.example.loyaltyrewardapp.data.module

import com.example.loyaltyrewardapp.data.viewmodel.ManagerScanViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ManagerScanModule {

    @Singleton
    @Provides
    fun provideManagerScanViewModel(): ManagerScanViewModel {
        return ManagerScanViewModelSingleton.getInstance()
    }
}
