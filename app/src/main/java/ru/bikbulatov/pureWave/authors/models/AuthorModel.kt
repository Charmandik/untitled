package ru.bikbulatov.pureWave.authors.models

import ru.bikbulatov.pureWave.podcasts.PodcastModel

class AuthorModel(
    val id: Int,
    val name: String,
    val position: String,
    val photo: String,
    val podcasts: List<PodcastModel>
)

