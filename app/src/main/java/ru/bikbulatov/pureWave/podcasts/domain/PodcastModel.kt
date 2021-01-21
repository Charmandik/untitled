package ru.bikbulatov.pureWave.podcasts.domain

import ru.bikbulatov.pureWave.authors.models.AuthorModel

class PodcastModel(
    val id: Int,
    val link: String,
    val language: String,
    val cover: String?,
    val author: List<AuthorModel>?,
    val description: String,
    val email: String,
    val title: String,
    val category: String,
    val lastBuildDate: String?,
    val tracksNum: Int
)
