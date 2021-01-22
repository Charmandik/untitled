package ru.bikbulatov.pureWave.data

import retrofit2.http.GET
import retrofit2.http.Path
import ru.bikbulatov.pureWave.authors.models.AuthorModel
import ru.bikbulatov.pureWave.podcasts.domain.PodcastCategorieModel

interface PodcastsApi {
    @GET("podcasts/")
    suspend fun getPodcasts(): List<PodcastCategorieModel>


    @GET("podcasts/{id}")
    suspend fun getPodcast(@Path("id") podcastId: Int): PodcastCategorieModel

    @GET("podcasts/{podcastId}/{songId}")
    suspend fun toggleLike(
        @Path("podcastId") podcastId: Int,
        @Path("songId") songId: Int
    ): AuthorModel
}