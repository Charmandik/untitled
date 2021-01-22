package ru.bikbulatov.pureWave.podcasts.domain

import ru.bikbulatov.pureWave.authors.models.AuthorModel

class PodcastCategorieModel(
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


//"file": "https:\/\/purewave.ru\/assets\/files\/mp3\/mysli-vsluh\/mysli-vsluh-n-28-pivo.wav",
//"createdon": "1543915349",
//"is_liked": false,
//"playtime_string": "2:33",
//"playtime_seconds": 152.972766439909293012533453293144702911376953125,
//"filesize": 26988794,
//"fileformat": "wav",
//"type": "audio\/wav",
//"filename": "mysli-vsluh-n-28-pivo.wav",
//"audio": {
//    :                 "sample_rate": 44100,
//    "bitrate": 1411200
//},
//"likes": 0
