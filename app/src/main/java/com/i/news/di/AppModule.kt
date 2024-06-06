package com.i.news.di

import android.app.Application
import com.i.news.data.manager.LocalUserMangerImpl
import com.i.news.data.remote.ApiService
import com.i.news.data.repository.NewsRepositoryImpl
import com.i.news.domain.manager.LocalUserManager
import com.i.news.domain.repository.NewsRepository
import com.i.news.domain.usecase.app_entry.AppEntryUseCase
import com.i.news.domain.usecase.app_entry.ReadAppEntry
import com.i.news.domain.usecase.app_entry.SaveAppEntry
import com.i.news.domain.usecase.news.GetAllNews
import com.i.news.domain.usecase.news.NewsUseCase
import com.i.news.domain.usecase.news.SearchNews
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    @Provides
    @Singleton
    fun provideApiService(): ApiService =
        Retrofit.Builder().baseUrl(ApiService.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideNewsRepository(apiService: ApiService): NewsRepository = NewsRepositoryImpl(apiService)

    @Provides
    @Singleton
    fun provideNewsUseCase(repository: NewsRepository): NewsUseCase = NewsUseCase(GetAllNews(repository), SearchNews(repository))
}