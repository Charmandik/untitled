package ru.bikbulatov.pureWave.podcasts.domain

import androidx.lifecycle.MutableLiveData
import ru.bikbulatov.pureWave.articles.models.LikeResponse
import ru.bikbulatov.pureWave.podcasts.domain.models.PodcastCategoryModel
import ru.bikbulatov.pureWave.podcasts.domain.models.PodcastModel

interface PodcastsRepo {
    suspend fun getPodcasts(podcasts: MutableLiveData<List<PodcastCategoryModel>>)
    suspend fun getPodcast(id: Int, podcasts: MutableLiveData<PodcastModel>)
    suspend fun toggleLike(
        podcastId: Int,
        songId: Int,
        like: MutableLiveData<LikeResponse>
    )
}