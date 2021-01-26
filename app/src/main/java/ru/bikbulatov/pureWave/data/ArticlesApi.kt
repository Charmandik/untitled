package ru.bikbulatov.pureWave.data

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import ru.bikbulatov.pureWave.articles.models.ArticleModel

interface ArticlesApi {
    @GET("articles")
    suspend fun getArticles(
        @Header("apikey") apiKey : String = Constants.API_KEY
    ): List<ArticleModel>


    @GET("articles/{id}")
    suspend fun getArticle(
        @Header("apikey") apiKey : String = Constants.API_KEY,
        @Path("id") articleId: Int): ArticleModel

}