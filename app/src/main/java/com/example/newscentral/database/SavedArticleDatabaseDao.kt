package com.example.newscentral.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SavedArticleDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(savedArticleEntity: SavedArticleEntity)

    @Query("select * from saved_articles order by rowId desc")
    fun getAll(): LiveData<List<SavedArticleEntity>>

    @Query("select * from saved_articles where url=:articleUrl")
    fun getArticle(articleUrl: String): LiveData<List<SavedArticleEntity>>

    @Query("select rowId + 1 from saved_articles order by rowId desc limit 1")
    suspend fun getNextId(): Long

}
