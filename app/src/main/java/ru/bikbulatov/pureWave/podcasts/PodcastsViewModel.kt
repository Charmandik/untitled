package ru.bikbulatov.pureWave.podcasts

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.bikbulatov.pureWave.podcasts.domain.PodcastCategorieModel
import ru.bikbulatov.pureWave.podcasts.domain.PodcastsRepo

class PodcastsViewModel @ViewModelInject constructor(val podcastsRepo: PodcastsRepo) : ViewModel() {
    val podcasts: MutableLiveData<List<PodcastCategorieModel>> = MutableLiveData()

    fun getPodcasts() {
        CoroutineScope(Dispatchers.IO).launch {
            podcastsRepo.getPodcasts(podcasts)
        }
    }

    val podcast: MutableLiveData<PodcastCategorieModel> = MutableLiveData()

    fun getPodcast(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            podcastsRepo.getPodcast(id, podcast)
        }
    }
}