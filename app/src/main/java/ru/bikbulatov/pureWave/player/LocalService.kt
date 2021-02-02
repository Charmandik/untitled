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


class LocalService : Service(), MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener {
    private val myBinder = MyLocalBinder()
    private var player: MediaPlayer? = null
    var file: String = ""

    fun initPlayer() {
        Log.d("test", "initPlayer")
        player = MediaPlayer().apply {
            setOnPreparedListener(this@LocalService)
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(file)
            prepareAsync()
        }
    }

    override fun onBind(intent: Intent): IBinder {
        Toast.makeText(applicationContext, "binding", Toast.LENGTH_SHORT).show()
        return myBinder
    }

    fun setDataToPlayer(_file: String) {
        Log.d("test", "setDataToPlayer")
        file = _file
        if (player == null)
            initPlayer()
        else {
            player?.reset()
            player?.setDataSource(file)
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


}