package ru.bikbulatov.pureWave.authors.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.bikbulatov.pureWave.authors.data.AuthorsRepo

class AuthorsVM @ViewModelInject constructor(val authorsRepo: AuthorsRepo) : ViewModel() {
    fun getAuthors() {
        CoroutineScope(Dispatchers.IO).launch {
            authorsRepo.getAuthors()
        }
    }

    fun getAuthor(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            authorsRepo.getAuthor(id)
        }
    }
}