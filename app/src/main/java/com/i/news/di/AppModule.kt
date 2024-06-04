package com.i.news.di

import android.app.Application
import com.i.news.data.manager.LocalUserMangerImpl
import com.i.news.domain.manager.LocalUserManager
import com.i.news.domain.usecase.AppEntryUseCase
import com.i.news.domain.usecase.ReadAppEntry
import com.i.news.domain.usecase.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(application: Application): LocalUserManager = LocalUserMangerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCase(localUserManager: LocalUserManager): AppEntryUseCase =
        AppEntryUseCase(SaveAppEntry(localUserManager), ReadAppEntry(localUserManager))
}