package ru.bikbulatov.pureWave.podcasts.domain.models

import ru.bikbulatov.pureWave.authors.models.AuthorModel

class PodcastModel(
    val id: Int,
    val link: String,
    val language: String,
    val author: List<AuthorModel>,
    val description: String,
    val email: String,
    val title: String,
    val cover: String,
    val category: String,
    val tracks: List<TrackModel>
)