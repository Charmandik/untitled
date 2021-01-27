package ru.bikbulatov.pureWave.player

import android.os.IBinder

interface IPlayerCommands : IBinder{
    fun startPlayer(url : String)
    fun stopPlayer()
    fun pausePlayer()
}