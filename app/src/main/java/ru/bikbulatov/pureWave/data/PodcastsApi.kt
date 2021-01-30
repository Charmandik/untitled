package ru.bikbulatov.pureWave.data

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import ru.bikbulatov.pureWave.authors.models.AuthorModel
import ru.bikbulatov.pureWave.podcasts.domain.models.PodcastCategoryModel
import ru.bikbulatov.pureWave.podcasts.domain.models.PodcastModel

interface PodcastsApi {
    @GET("podcasts/")
    suspend fun getPodcasts(
        @Header("apikey") apiKey: String = Constants.API_KEY
    ): List<PodcastCategoryModel>


    @GET("podcasts/{id}")
    suspend fun getPodcast(
        @Header("apikey") apiKey: String = Constants.API_KEY,
        @Path("id") podcastId: Int
    ): PodcastModel

    //todo изменить модель
    @GET("podcasts/{podcastId}/{songId}")
    suspend fun toggleLike(
        @Header("apikey") apiKey: String = Constants.API_KEY,
        @Path("podcastId") podcastId: Int,
        @Path("songId") songId: Int
    ): AuthorModel
}