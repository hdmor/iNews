package com.i.news.data.remote

import com.i.news.data.remote.dto.NewsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("everything")
    suspend fun getAllNews(@Query("page") page: Int, @Query("sources") sources: String, @Query("apiKey") apiKey: String = API_KEY): NewsDto

    @GET("everything")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = API_KEY
    ) : NewsDto

    companion object {
        const val BASE_URL = "https://newsapi.org/v2/"
        const val API_KEY = "afcee8943aa749f69ee9c2af07806f46"
    }
}