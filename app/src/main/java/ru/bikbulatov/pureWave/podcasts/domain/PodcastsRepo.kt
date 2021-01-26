package ru.bikbulatov.pureWave.podcasts.domain

import androidx.lifecycle.MutableLiveData
import ru.bikbulatov.pureWave.podcasts.domain.models.PodcastCategorieModel
import ru.bikbulatov.pureWave.podcasts.domain.models.PodcastModel

interface PodcastsRepo {
    suspend fun getPodcasts(podcasts: MutableLiveData<List<PodcastCategorieModel>>)
    suspend fun getPodcast(id: Int, podcasts: MutableLiveData<PodcastModel>)
}