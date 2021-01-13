package ru.bikbulatov.pureWave.articles.data

interface ArticlesRepo {
    suspend fun getArticles()
    suspend fun getArticle(id: Int)
}