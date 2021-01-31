package ru.bikbulatov.pureWave

import android.app.Application
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp
import java.util.*

@HiltAndroidApp
class BaseApp : Application() {
    companion object {
        lateinit var instance: BaseApp
        var sharedPreferences: SharedPreferences? = null
        const val SP_DEVICE_ID = "deviceId"
    }

    override fun onCreate() {
        super.onCreate()
        sharedPreferences = getSharedPreferences("pureWave", MODE_PRIVATE)
        instance = this
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    fun getDeviceId(): String {
        if (!isDeviceIdCreated())
            createDeviceId()
        return sharedPreferences?.getString(SP_DEVICE_ID, "")!!
    }

    fun createDeviceId() {
        sharedPreferences?.edit()?.putString(SP_DEVICE_ID, UUID.randomUUID().toString())?.apply()
    }

    fun isDeviceIdCreated(): Boolean {
        return sharedPreferences?.contains(SP_DEVICE_ID) == true
    }
}