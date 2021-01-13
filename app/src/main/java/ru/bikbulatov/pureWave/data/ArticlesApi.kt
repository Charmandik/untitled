package ru.bikbulatov.pureWave.data

import retrofit2.http.*
import ru.bikbulatov.pureWave.articles.models.ArticleModel

interface ArticlesApi {
    /**
     * Updates access and refresh tokens via refreshToken
     * @param refreshToken
     * @return access and refresh tokens
     */
    @GET("articles")
    suspend fun getArticles(): List<ArticleModel>

    /**
     * Updates access and refresh tokens via refreshToken
     * @param refreshToken
     * @return access and refresh tokens
     */
    @GET("articles/{id}")
    suspend fun getArticle(@Path("id") articleId: Int): ArticleModel

}