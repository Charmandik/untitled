package ru.bikbulatov.pureWave.podcasts.domain.models

import com.google.gson.annotations.SerializedName

class AudioDetails(
    @SerializedName("sample_rate")
    val sampleRate: Int,
    val bitrate: Int
)

