package ru.bikbulatov.pureWave.authors.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import ru.bikbulatov.pureWave.authors.models.AuthorModel
import ru.bikbulatov.pureWave.serverApi.NetworkHolder


class AuthorsRepoImpl : AuthorsRepo {
    override suspend fun getAuthors(authors: MutableLiveData<List<AuthorModel>>) {
        try {
            val response = NetworkHolder.apiRepository.authorsApi.getAuthors()
            response.let {
                authors.postValue(response)
                Log.d("test123", "test222")
            }
        } catch (e: Exception) {
            Log.d("test123", "getAuthor error ${e.message}")
        }
    }

    override suspend fun getAuthor(id: Int, author: MutableLiveData<AuthorModel>) {
        try {
            val response = NetworkHolder.apiRepository.authorsApi.getAuthor(authorId = id)
            response.let {
                author.postValue(response)
                Log.d("test123", "test222")
            }
        } catch (e: Exception) {
            Log.d("test123", "getAuthor id error ${e.message}")
        }
    }
}