package ru.bikbulatov.pureWave.serverApi

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import ru.bikbulatov.pureWave.BaseApp
import ru.bikbulatov.pureWave.articles.models.ArticleModel
import ru.bikbulatov.pureWave.articles.models.LikeResponse

interface ArticlesApi {
    @GET("articles")
    suspend fun getArticles(
        @Header("appKey") appKey: String = BaseApp.instance.getDeviceId(),
        @Header("apikey") apiKey: String = Constants.API_KEY
    ): List<ArticleModel>


    @GET("articles/{id}")
    suspend fun getArticle(
        @Header("apikey") apiKey: String = Constants.API_KEY,
        @Header("appKey") appKey: String = BaseApp.instance.getDeviceId(),
        @Path("id") articleId: Int
    ): ArticleModel

    //todo переделать модель
    @POST("articles/{articleId}")
    suspend fun toggleLike(
        @Header("apikey") apiKey: String = Constants.API_KEY,
        @Header("appKey") appKey: String = BaseApp.instance.getDeviceId(),
        @Path("articleId") articleId: Int
    ): LikeResponse
}