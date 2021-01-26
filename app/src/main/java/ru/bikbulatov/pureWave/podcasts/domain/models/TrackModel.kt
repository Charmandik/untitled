package ru.bikbulatov.pureWave.podcasts.domain.models

import com.google.gson.annotations.SerializedName

class TrackModel(
    val id: Int,
    val title: String,
    val description: String,
    val file: String,
    @SerializedName("createdon")
    val createdOn: Int,
    @SerializedName("is_liked")
    val isLiked: Boolean,
    @SerializedName("playtime_string")
    val playtimeString: String,
    @SerializedName("playtime_seconds")
    val playtimeSeconds: Float,
    val filesize: Int,
    val fileformat: String,
    val type: String,
    val filename: String,
    val audio: AudioDetails,
    val likes: Int
)