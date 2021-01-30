package ru.bikbulatov.pureWave.authors.models

import com.google.gson.annotations.SerializedName
import ru.bikbulatov.pureWave.podcasts.domain.models.PodcastCategoryModel

class AuthorModel(
    val id: Int,
    val name: String,
    val position: String,
    val photo: String,
    @SerializedName("created_on")
    val createdOn: String,
    val podcasts: List<PodcastCategoryModel>
)

