package ru.bikbulatov.pureWave.authors.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.bikbulatov.pureWave.authors.data.AuthorsRepo
import ru.bikbulatov.pureWave.authors.models.AuthorModel

class AuthorsVM @ViewModelInject constructor(val authorsRepo: AuthorsRepo) : ViewModel() {
    val authors: MutableLiveData<List<AuthorModel>> = MutableLiveData()
    fun getAuthors() {
        CoroutineScope(Dispatchers.IO).launch {
            authorsRepo.getAuthors(authors)
        }
    }

    val author: MutableLiveData<AuthorModel> = MutableLiveData()
    fun getAuthor(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            authorsRepo.getAuthor(id, author)
        }
    }
}