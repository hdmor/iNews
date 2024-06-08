package com.i.news.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.i.news.domain.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Query("SELECT * FROM Article")
    fun selectAllArticles(): Flow<List<Article>>

    @Query("SELECT * FROM Article WHERE URL = :url")
    suspend fun getArticle(url: String): Article?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article)

    @Delete
    suspend fun delete(article: Article)
}