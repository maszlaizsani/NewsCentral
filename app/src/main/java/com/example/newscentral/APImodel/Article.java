package com.example.newscentral.APImodel;

import com.example.newscentral.database.SavedArticleEntity;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Article implements Serializable {
    @SerializedName("source")
    @Expose
    private Source source;
    @SerializedName("author")
    @Expose
    private String author;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("urlToImage")
    @Expose
    private String urlToImage;
    @SerializedName("publishedAt")
    @Expose
    private String publishedAt;
    @SerializedName("content")
    @Expose
    private String content;

    public Article(String author, String title, String content, String url, String urlToImage) {
        this.author= author;
        this.title = title;
        this.content = content;
        this.url = url;
        this.urlToImage = urlToImage;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static SavedArticleEntity convertApiModelToEntity(Article article) {
        return new SavedArticleEntity(
                1,
                article.source.toString(),
                article.author == null ? "null" : article.author,
                article.title == null ? "null" : article.title,
                article.description == null ? "null" : article.description,
                article.url == null ? "null" : article.url,
                article.urlToImage == null ? "null" : article.urlToImage,
                article.publishedAt == null ? "null" : article.publishedAt,
                article.content == null ? "null" : article.content);
    }

    public static Article convertEntityToApiModel(SavedArticleEntity savedArticleEntity){
        return new Article(
                savedArticleEntity.getAuthor(),
                savedArticleEntity.getTitle(),
                savedArticleEntity.getContent(),
                savedArticleEntity.getUrl(),
                savedArticleEntity.getUrlToImage());
    }

}
