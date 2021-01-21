package ru.bikbulatov.pureWave.podcasts.domain

import androidx.lifecycle.MutableLiveData

interface PodcastsRepo {
    suspend fun getPodcasts(podcasts: MutableLiveData<List<PodcastModel>>)
    suspend fun getPodcast(id: Int, podcasts: MutableLiveData<PodcastModel>)
}