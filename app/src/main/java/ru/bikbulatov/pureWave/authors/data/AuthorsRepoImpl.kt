package ru.bikbulatov.pureWave.authors.data

import android.util.Log
import ru.bikbulatov.pureWave.data.NetworkHolder


class AuthorsRepoImpl : AuthorsRepo {
    override suspend fun getAuthors() {
        try {
            val response = NetworkHolder.apiRepository.authorsApi.getAuthors()
            response?.let {
                Log.d("test123", "test222")
            }
        } catch (e: Exception) {
            Log.d("test123", "getAuthor error ${e.message}")
        }
    }

    override suspend fun getAuthor(id: Int) {
        try {
            val response = NetworkHolder.apiRepository.authorsApi.getAuthor(id)
            response?.let {
                Log.d("test123", "test222")
            }
        } catch (e: Exception) {
            Log.d("test123", "getAuthor id error ${e.message}")
        }
    }
}