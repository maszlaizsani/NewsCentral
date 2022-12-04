package com.example.newscentral.service;

import com.example.newscentral.APImodel.News
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://newsapi.org/"
var retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .build()

interface NewsAPIService {
    @GET("v2/top-headlines?country=hu&apiKey=347e9a09ce334ba49b3bd458fa0393d6")
    suspend fun getHeadlinesInHungary(): News
}

object NewsApi {
    val newsAPIService: NewsAPIService by lazy {
        retrofit.create(NewsAPIService::class.java)
    }
}