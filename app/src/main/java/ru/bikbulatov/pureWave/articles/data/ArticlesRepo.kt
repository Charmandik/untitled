package ru.bikbulatov.pureWave.articles.data

import androidx.lifecycle.MutableLiveData
import ru.bikbulatov.pureWave.articles.models.ArticleModel
import ru.bikbulatov.pureWave.articles.models.LikeResponse

interface ArticlesRepo {
    suspend fun getArticles(articles: MutableLiveData<List<ArticleModel>>)
    suspend fun getArticle(id: Int, article: MutableLiveData<ArticleModel>)
    suspend fun toggleLike(id: Int, like: MutableLiveData<LikeResponse>)
}