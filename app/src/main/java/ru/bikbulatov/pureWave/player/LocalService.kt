package ru.bikbulatov.pureWave.player

import android.app.Service
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import java.util.*


class LocalService : Service(), MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener,
    MediaPlayer.OnCompletionListener, MediaPlayer.OnSeekCompleteListener {
    private val myBinder = MyLocalBinder()
    private var player: MediaPlayer? = null
    var trackList: MutableList<String> = mutableListOf()
    var currentPosition = 0

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
            setDataSource(trackList.first())
            prepareAsync()
        }
    }

    override fun onBind(intent: Intent): IBinder {
        Toast.makeText(applicationContext, "binding", Toast.LENGTH_SHORT).show()
        return myBinder
    }

    fun setDataToPlayer(_file: String) {
        Log.d("test", "setDataToPlayer")
        if (player == null)
            initPlayer()
        else {
            player?.reset()
            player?.setDataSource(_file)
            player?.prepareAsync()
        }
        Log.d("test", player?.isPlaying.toString())
    }

    fun setDataToPlayer(_trackList: List<String>) {
        Log.d("test", "trackList setDataToPlayer")
        trackList = _trackList as MutableList<String>
        if (player == null)
            initPlayer()
        else {
            player?.reset()
            player?.setDataSource(trackList.first())
            player?.prepareAsync()
        }
        Log.d("test", player?.isPlaying.toString())
    }

    fun pausePlayer() {
        player?.pause()
    }

    override fun onPrepared(mp: MediaPlayer?) {
        Log.d("test", "playerPrepared")
        player?.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        player?.release()
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
        }
        Log.d("test", "onCompletion")
    }

    override fun onSeekComplete(mp: MediaPlayer?) {
        Log.d("test", "onSeekComplete")
        Log.d("test", "onSeekComplete")
        Log.d("test", "onSeekComplete")
        Log.d("test", "onSeekComplete")
        Log.d("test", "onSeekComplete")
    }
}