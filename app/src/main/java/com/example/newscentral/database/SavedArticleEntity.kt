package com.example.newscentral.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_articles")
data class SavedArticleEntity(
    @PrimaryKey(autoGenerate = true)
    var articleId: Long = 0L,
    @ColumnInfo(name = "source")
    val source: String,
    @ColumnInfo(name = "author")
    var author: String,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "description")
    var description: String,
    @ColumnInfo(name = "url")
    var url: String,
    @ColumnInfo(name = "url_to_image")
    var urlToImage: String,
    @ColumnInfo(name = "publishedAt")
    var publishedAt: String,
    @ColumnInfo(name = "content")
    var content: String
)