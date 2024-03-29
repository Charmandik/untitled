package ru.bikbulatov.pureWave.articles.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import ru.bikbulatov.pureWave.articles.models.ArticleModel
import ru.bikbulatov.pureWave.articles.models.LikeResponse
import ru.bikbulatov.pureWave.serverApi.NetworkHolder

class ArticlesRepoImpl : ArticlesRepo {
    override suspend fun getArticles(articles: MutableLiveData<List<ArticleModel>>) {
        try {
            val response = NetworkHolder.apiRepository.articlesApi.getArticles()
            response.let {
                articles.postValue(response)
                Log.d("test123", "test222")
            }
        } catch (e: Exception) {
            Log.d("test123", "Articles error ${e.message}")
        }
    }

    override suspend fun getArticle(id: Int, article: MutableLiveData<ArticleModel>) {
        try {
            val response = NetworkHolder.apiRepository.articlesApi.getArticle(articleId = id)
            response.let {
                article.postValue(response)
                Log.d("test123", "test222")
            }
        } catch (e: Exception) {
            Log.d("test123", "Articles id error ${e.message}")
        }
    }

    override suspend fun toggleLike(id: Int, like: MutableLiveData<LikeResponse>) {
        try {
            val response = NetworkHolder.apiRepository.articlesApi.toggleLike(articleId = id)
            response.let {
                like.postValue(response)
                Log.d("test123", "test222")
            }
        } catch (e: Exception) {
            Log.d("test123", "Articles id error ${e.message}")
        }
    }
}