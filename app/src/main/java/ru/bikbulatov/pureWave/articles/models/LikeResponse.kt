package ru.bikbulatov.pureWave.articles.models

import com.google.gson.annotations.SerializedName

class LikeResponse(
    val status: String,
    val operation: String,
    @SerializedName("appkey")
    val appKey: String,
    val likes: Int
)