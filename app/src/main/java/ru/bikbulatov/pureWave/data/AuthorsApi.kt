package ru.bikbulatov.pureWave.data

import retrofit2.http.GET
import retrofit2.http.Path
import ru.bikbulatov.pureWave.authors.models.AuthorModel

interface AuthorsApi {
    @GET("authors")
    suspend fun getAuthors(): List<AuthorModel>


    @GET("authors/{id}")
    suspend fun getAuthor(@Path("id") authorId: Int): AuthorModel

}