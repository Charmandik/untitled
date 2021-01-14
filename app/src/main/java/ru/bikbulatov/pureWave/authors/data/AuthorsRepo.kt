package ru.bikbulatov.pureWave.authors.data

import androidx.lifecycle.MutableLiveData
import ru.bikbulatov.pureWave.authors.models.AuthorModel

interface AuthorsRepo {
    suspend fun getAuthors(authors: MutableLiveData<List<AuthorModel>>)
    suspend fun getAuthor(id: Int, author: MutableLiveData<AuthorModel>)
}