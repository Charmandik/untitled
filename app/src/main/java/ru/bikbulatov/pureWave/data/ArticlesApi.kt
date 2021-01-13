package ru.bikbulatov.pureWave.data

import retrofit2.http.GET
import retrofit2.http.Path
import ru.bikbulatov.pureWave.articles.models.ArticleModel

interface ArticlesApi {
    @GET("articles")
    suspend fun getArticles(): List<ArticleModel>


    @GET("articles/{id}")
    suspend fun getArticle(@Path("id") articleId: Int): ArticleModel

}