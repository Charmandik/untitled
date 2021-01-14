package ru.bikbulatov.pureWave.articles.data

import androidx.lifecycle.MutableLiveData
import ru.bikbulatov.pureWave.articles.models.ArticleModel

interface ArticlesRepo {
    suspend fun getArticles(articles: MutableLiveData<List<ArticleModel>>)
    suspend fun getArticle(id: Int, article: MutableLiveData<ArticleModel>)
}