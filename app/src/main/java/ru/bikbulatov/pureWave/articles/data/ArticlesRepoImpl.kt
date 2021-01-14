package ru.bikbulatov.pureWave.articles.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import ru.bikbulatov.pureWave.articles.models.ArticleModel
import ru.bikbulatov.pureWave.data.NetworkHolder

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
            val response = NetworkHolder.apiRepository.articlesApi.getArticle(id)
            response.let {
                article.postValue(response)
                Log.d("test123", "test222")
            }
        } catch (e: Exception) {
            Log.d("test123", "Articles id error ${e.message}")
        }
    }
}