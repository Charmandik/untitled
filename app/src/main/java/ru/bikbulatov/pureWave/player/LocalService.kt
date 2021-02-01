package ru.bikbulatov.pureWave.player

import android.app.Service
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*


class LocalService : Service(), MediaPlayer.OnPreparedListener {
    private val myBinder = MyLocalBinder()
    private var player: MediaPlayer? = null
    var file: String = ""

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    fun initPlayer() {
        player?.apply {
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
        file = _file
        if (player == null) {
            initPlayer()
        } else
            player?.setDataSource(file)
        Log.d("test", player?.isPlaying.toString())
    }

    fun getCurrentTime(): String {
        val dateformat = SimpleDateFormat(
            "HH:mm:ss MM/dd/yyyy",
            Locale.US
        )
        return dateformat.format(Date())
    }

    override fun onPrepared(mp: MediaPlayer?) {
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
}