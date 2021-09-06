package com.example.interop.api

import com.example.interop.model.NewsModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://newsapi.org/"
const val API_KEY = "328ecc9252904a7789972700cc47f4be"

interface NewsInterface {

    @GET("v2/everything?apiKey=$API_KEY")
    suspend fun getNews(@Query("q") category: String, @Query("page") page: Int): NewsModel
}

//https://newsapi.org/v2/everything?apiKey=328ecc9252904a7789972700cc47f4be&page=1
object NewsService {
    val newsInstance: NewsInterface

    init {
        val retrofit = Retrofit.Builder().apply {
            baseUrl(BASE_URL)
            addConverterFactory(GsonConverterFactory.create())
        }.build()
        newsInstance = retrofit.create(NewsInterface::class.java)
    }
}
