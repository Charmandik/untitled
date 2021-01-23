package ru.bikbulatov.pureWave.articles.models

import com.google.gson.annotations.SerializedName

class ArticleModel(
    val id: Int,
    val title: String,
    val author : String,
    val intro: String,
    val cover: String,
    @SerializedName("created_on")
    val createdOn: Long,
    val likes : Int,
    @SerializedName("is_liked")
    val isLiked : Boolean,
    val content : String
)
