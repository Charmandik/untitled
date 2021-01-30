package ru.bikbulatov.pureWave.podcasts.domain.models

import ru.bikbulatov.pureWave.authors.models.AuthorModel

class PodcastCategoryModel(
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
