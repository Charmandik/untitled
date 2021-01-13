package ru.bikbulatov.pureWave.data

import retrofit2.http.GET
import retrofit2.http.Path
import ru.bikbulatov.pureWave.podcasts.PodcastModel

interface PodcastsApi {
    @GET("podcasts/")
    suspend fun getPodcasts(): List<PodcastModel>


    @GET("podcasts/{id}")
    suspend fun getPodcast(@Path("id") podcastId: Int): PodcastModel
}