package ru.bikbulatov.pureWave.podcasts.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import ru.bikbulatov.pureWave.articles.models.LikeResponse
import ru.bikbulatov.pureWave.podcasts.domain.PodcastsRepo
import ru.bikbulatov.pureWave.podcasts.domain.models.PodcastCategoryModel
import ru.bikbulatov.pureWave.podcasts.domain.models.PodcastModel
import ru.bikbulatov.pureWave.serverApi.NetworkHolder


class PodcastsRepoImpl : PodcastsRepo {
    override suspend fun getPodcasts(podcasts: MutableLiveData<List<PodcastCategoryModel>>) {
        try {
            val response = NetworkHolder.apiRepository.podcastsApi.getPodcasts()
            response.let {
                podcasts.postValue(response)
                Log.d("test123", "test222")
            }
        } catch (e: Exception) {
            Log.d("test123", "getAuthor error ${e.message}")
        }
    }

    override suspend fun getPodcast(id: Int, podcast: MutableLiveData<PodcastModel>) {
        try {
            val response = NetworkHolder.apiRepository.podcastsApi.getPodcast(podcastId = id)
            response.let {
                podcast.postValue(response)
                Log.d("test123", "test222")
            }
        } catch (e: Exception) {
            Log.d("test123", "getAuthor id error ${e.message}")
        }
    }

    override suspend fun toggleLike(
        podcastId: Int,
        songId: Int,
        like: MutableLiveData<LikeResponse>
    ) {
        try {
            val response = NetworkHolder.apiRepository.podcastsApi.toggleLike(
                podcastId = podcastId,
                songId = songId
            )
            response.let {
                like.postValue(response)
                Log.d("test123", "test222")
            }
        } catch (e: Exception) {
            Log.d("test123", "Articles id error ${e.message}")
        }
    }
}