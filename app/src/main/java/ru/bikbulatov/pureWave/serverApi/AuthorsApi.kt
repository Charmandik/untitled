package ru.bikbulatov.pureWave.serverApi

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import ru.bikbulatov.pureWave.authors.models.AuthorModel

interface AuthorsApi {
    @GET("authors")
    suspend fun getAuthors(
        @Header("apikey") apiKey : String = Constants.API_KEY
    ): List<AuthorModel>


    @GET("authors/{id}")
    suspend fun getAuthor(
        @Header("apikey") apiKey : String = Constants.API_KEY,
        @Path("id") authorId: Int): AuthorModel

}