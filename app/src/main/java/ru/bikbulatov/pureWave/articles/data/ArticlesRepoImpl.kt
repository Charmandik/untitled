package ru.bikbulatov.pureWave.articles.data

import android.util.Log
import ru.bikbulatov.pureWave.data.NetworkHolder

class ArticlesRepoImpl : ArticlesRepo {
    override suspend fun getArticles() {
        try {
            val response = NetworkHolder.apiRepository.articlesApi.getArticles()
            response?.let {
                Log.d("test123", "test222")
            }
        } catch (e: Exception) {
            Log.d("test123", "Articles error ${e.message}")
        }
    }

    override suspend fun getArticle(id: Int) {
        try {
            val response = NetworkHolder.apiRepository.articlesApi.getArticle(id)
            response?.let {
                Log.d("test123", "test222")
            }
        } catch (e: Exception) {
            Log.d("test123", "Articles id error ${e.message}")
        }
    }
}