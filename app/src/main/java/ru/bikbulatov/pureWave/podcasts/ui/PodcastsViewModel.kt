package ru.bikbulatov.pureWave.podcasts.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.bikbulatov.pureWave.articles.models.LikeResponse
import ru.bikbulatov.pureWave.podcasts.domain.PodcastsRepo
import ru.bikbulatov.pureWave.podcasts.domain.models.PodcastCategoryModel
import ru.bikbulatov.pureWave.podcasts.domain.models.PodcastModel

class PodcastsViewModel @ViewModelInject constructor(val podcastsRepo: PodcastsRepo) : ViewModel() {
    var podcastsAllLoadedSize: Int = 0
    var singlePodcastId: Int = 0

    val podcastsList: MutableLiveData<List<PodcastCategoryModel>> = MutableLiveData()
    fun getPodcasts() {
        CoroutineScope(Dispatchers.IO).launch {
            podcastsRepo.getPodcasts(podcastsList)
        }
    }

    val singlePodcast: MutableLiveData<PodcastModel> = MutableLiveData()
    fun getPodcast(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            podcastsRepo.getPodcast(id, singlePodcast)
        }
    }

    val likeResponse: MutableLiveData<LikeResponse> = MutableLiveData()
    fun toggleLike(
        podcastId: Int,
        songId: Int
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            podcastsRepo.toggleLike(podcastId, songId, likeResponse)
            getPodcast(singlePodcastId)
        }
    }
}