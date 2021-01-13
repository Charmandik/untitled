package ru.bikbulatov.pureWave.authors.data

interface AuthorsRepo {
    suspend fun getAuthors()
    suspend fun getAuthor(id: Int)
}