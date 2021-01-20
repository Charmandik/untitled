package ru.bikbulatov.pureWave.data

import javax.inject.Inject

class ApiRepository @Inject constructor(
    val articlesApi: ArticlesApi,
    val authorsApi: AuthorsApi,
    val podcastsApi: PodcastsApi,
    val utilsApi: UtilsApi
) {

}
