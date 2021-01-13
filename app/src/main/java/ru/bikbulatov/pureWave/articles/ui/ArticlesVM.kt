package ru.bikbulatov.pureWave.articles.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.bikbulatov.pureWave.authors.data.AuthorsRepo

class ArticlesVM @ViewModelInject constructor(val articlesRepo: AuthorsRepo) : ViewModel() {
    fun getArticles() {
        CoroutineScope(Dispatchers.IO).launch {
            articlesRepo.getArticles()
        }
    }

    fun getArticle(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            articlesRepo.getArticle(id)
        }
    }
}