package ru.bikbulatov.pureWave.articles.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.bikbulatov.pureWave.articles.data.ArticlesRepo
import ru.bikbulatov.pureWave.articles.models.ArticleModel

class ArticlesVM @ViewModelInject constructor(val articlesRepo: ArticlesRepo) : ViewModel() {
    val articles: MutableLiveData<List<ArticleModel>> = MutableLiveData()
    fun getArticles() {
        CoroutineScope(Dispatchers.IO).launch {
            articlesRepo.getArticles(articles)
        }
    }


     val singleArticle: MutableLiveData<ArticleModel> = MutableLiveData()
    fun getArticle(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            articlesRepo.getArticle(id, singleArticle)
        }
    }
}