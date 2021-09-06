package com.example.interop.model

data class ArticleModel(
    val title: String,
    val urlToImage: String,
    val description: String,
    val url: String
)

data class NewsModel(
    val totalResults: Int,
    val articles: List<ArticleModel>,
    var categories: List<String>
)

