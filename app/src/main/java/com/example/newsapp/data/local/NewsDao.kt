package com.example.newsapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.newsapp.domain.model.News
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Query("SELECT * FROM news")
    fun getAll(): LiveData<List<News>?>

    @Query("SELECT * FROM news WHERE is_favorite = 1")
    fun loadFavorites(): LiveData<List<News>?>

    @Insert(onConflict = IGNORE)
    suspend fun insertAll(news: List<News>?): LongArray

    @Insert(onConflict = REPLACE)
    suspend fun insert(news: News)

    @Query("UPDATE news SET is_favorite = :isFavorite WHERE id =:id")
    suspend fun update(isFavorite: Boolean, id: String)
}