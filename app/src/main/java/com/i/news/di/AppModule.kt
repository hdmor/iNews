package com.i.news.di

import android.app.Application
import androidx.room.Room
import com.i.news.data.manager.LocalUserMangerImpl
import com.i.news.data.repository.NewsRepositoryImpl
import com.i.news.data.source.local.NewsDao
import com.i.news.data.source.local.NewsDatabase
import com.i.news.data.source.local.NewsTypeConverter
import com.i.news.data.source.remote.ApiService
import com.i.news.domain.manager.LocalUserManager
import com.i.news.domain.repository.NewsRepository
import com.i.news.domain.usecase.app_entry.AppEntryUseCase
import com.i.news.domain.usecase.app_entry.ReadAppEntry
import com.i.news.domain.usecase.app_entry.SaveAppEntry
import com.i.news.domain.usecase.news.DeleteArticle
import com.i.news.domain.usecase.news.GetAllArticles
import com.i.news.domain.usecase.news.GetAllNews
import com.i.news.domain.usecase.news.NewsUseCase
import com.i.news.domain.usecase.news.SearchArticle
import com.i.news.domain.usecase.news.SearchNews
import com.i.news.domain.usecase.news.StoreArticle
import com.i.news.util.Constants
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
    fun provideNewsRepository(apiService: ApiService, newsDao: NewsDao): NewsRepository = NewsRepositoryImpl(apiService, newsDao)

    @Provides
    @Singleton
    fun provideNewsUseCase(repository: NewsRepository): NewsUseCase =
        NewsUseCase(
            GetAllNews(repository),
            SearchNews(repository),
            GetAllArticles(repository),
            SearchArticle(repository),
            StoreArticle(repository),
            DeleteArticle(repository)
        )

    @Provides
    @Singleton
    fun provideNewsDatabase(application: Application): NewsDatabase =
        Room.databaseBuilder(application, NewsDatabase::class.java, Constants.NEWS_DATABASE_NAME).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideNewsDao(database: NewsDatabase): NewsDao = database.newsDao
}