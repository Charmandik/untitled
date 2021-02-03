package ru.bikbulatov.pureWave.player

import android.app.Service
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import ru.bikbulatov.pureWave.podcasts.domain.models.TrackModel
import java.util.*


class LocalService : Service(), MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener,
    MediaPlayer.OnCompletionListener, MediaPlayer.OnSeekCompleteListener {
    private val myBinder = MyLocalBinder()
    private var player: MediaPlayer? = null
    var trackList: MutableList<TrackModel> = mutableListOf()
    var currentTrack: MutableLiveData<TrackModel> = MutableLiveData()
    var currentPosition = 0
    var currentVolume = 0.5F


    fun initPlayer() {
        Log.d("test", "initPlayer")
        player = MediaPlayer().apply {
            setOnPreparedListener(this@LocalService)
            setOnErrorListener(this@LocalService)
            setOnCompletionListener(this@LocalService)
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(trackList.first().file)
            prepareAsync()
        }
    }

    override fun onBind(intent: Intent): IBinder {
        Toast.makeText(applicationContext, "binding", Toast.LENGTH_SHORT).show()
        return myBinder
    }

    fun setDataToPlayer(_track: TrackModel) {
        Log.d("test", "setDataToPlayer")
        if (player == null)
            initPlayer()
        else {
            player?.reset()
            player?.setDataSource(_track.file)
            player?.prepareAsync()
        }
        Log.d("test", player?.isPlaying.toString())
    }

    fun setDataToPlayer(_trackList: List<TrackModel>) {
        Log.d("test", "trackList setDataToPlayer")
        trackList = _trackList as MutableList<TrackModel>
        if (player == null)
            initPlayer()
        else {
            player?.reset()
            player?.setDataSource(trackList.first().file)
            player?.prepareAsync()
        }
        Log.d("test", player?.isPlaying.toString())
    }

    fun pausePlayer() {
        player?.pause()
    }

    fun turnUpVolume() {
        if (currentVolume < 1F)
            currentVolume += 0.1F
        player?.setVolume(currentVolume, currentVolume)

    }

    fun turnDownVolume() {
        if (currentVolume > 0F)
            currentVolume -= 0.1F
        player?.setVolume(currentVolume, currentVolume)
    }

    fun getCurrentTrack(): TrackModel? {
        return if (player?.isPlaying == true)
            trackList[currentPosition]
        else null
    }

    override fun onPrepared(mp: MediaPlayer?) {
        Log.d("test", "playerPrepared")
        player?.start()
        currentTrack.value = trackList[currentPosition]
    }

    override fun onDestroy() {
        super.onDestroy()
        player?.release()
        currentTrack.value = null
    }

    inner class MyLocalBinder : Binder() {
        fun getService(): LocalService {
            return this@LocalService
        }
    }

    override fun onError(mp: MediaPlayer?, what: Int, extra: Int): Boolean {
        when (what) {
            MediaPlayer.MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK -> Log.d(
                "MediaPlayer Error",
                "MEDIA ERROR NOT VALID FOR PROGRESSIVE PLAYBACK $extra"
            )
            MediaPlayer.MEDIA_ERROR_SERVER_DIED -> Log.d(
                "MediaPlayer Error",
                "MEDIA ERROR SERVER DIED $extra"
            )
            MediaPlayer.MEDIA_ERROR_UNKNOWN -> Log.d(
                "MediaPlayer Error",
                "MEDIA ERROR UNKNOWN $extra"
            )
            else ->
                Log.d(
                    "MediaPlayer Error",
                    "MEDIA ERROR ELSE"
                )
        }
        return false
    }

    override fun onCompletion(mp: MediaPlayer?) {
        if (currentPosition + 1 < trackList.size) {
            currentPosition += 1
            setDataToPlayer(trackList[currentPosition])
            currentTrack.value = trackList[currentPosition]
        }
        Log.d("test", "onCompletion")
    }

    override fun onSeekComplete(mp: MediaPlayer?) {
        Log.d("test", "onSeekComplete")
    }
}