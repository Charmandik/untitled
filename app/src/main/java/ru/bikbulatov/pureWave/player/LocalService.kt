package ru.bikbulatov.pureWave.player

import android.app.Service
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*


class LocalService : Service() {
    private val myBinder = MyLocalBinder()
    lateinit var player: MediaPlayer

    /**
     * When binding to the service, we return an interface to our messenger
     * for sending messages to the service.
     */
    override fun onBind(intent: Intent): IBinder? {
        Toast.makeText(applicationContext, "binding", Toast.LENGTH_SHORT).show()
        return myBinder
    }

    override fun onCreate() {
        super.onCreate()
        player = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )

        }
    }

    fun setDataToPlayer(file: String) {
        player.prepare() // might take long! (for buffering, etc)
        player.start()
        player.setDataSource(file)

    }

    fun getCurrentTime(): String {
        val dateformat = SimpleDateFormat(
            "HH:mm:ss MM/dd/yyyy",
            Locale.US
        )
        return dateformat.format(Date())
    }

    inner class MyLocalBinder : Binder() {
        fun getService(): LocalService {
            return this@LocalService
        }
    }
}