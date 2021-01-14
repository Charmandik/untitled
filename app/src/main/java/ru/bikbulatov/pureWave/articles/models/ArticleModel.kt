package ru.bikbulatov.pureWave.articles.models

import com.google.gson.annotations.SerializedName

class ArticleModel(
    val id: Int,
    val title: String,
    val intro: String,
    @SerializedName("created_on")
    val createdOn: Long
)
