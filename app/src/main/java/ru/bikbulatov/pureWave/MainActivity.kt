package ru.bikbulatov.pureWave

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import dagger.hilt.android.AndroidEntryPoint
import ru.bikbulatov.pureWave.articles.ui.FragmentArticles
import ru.bikbulatov.pureWave.databinding.ActivityMainBinding
import ru.bikbulatov.pureWave.mainWindow.FragmentPicker
import ru.bikbulatov.pureWave.player.LocalService
import ru.bikbulatov.pureWave.podcasts.ui.FragmentPodcastsCategories

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    companion object {
        lateinit var instance: MainActivity
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var serviceConnection: ServiceConnection
    private lateinit var playerService: LocalService
    private var isBound = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        instance = this
        configureBottomNavigation()
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(
                binding.flContainer.id,
                FragmentPicker()
            ) //its true position of picker
        }
        serviceConnection = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                Log.d("test123", "onServiceConnected")
                val binder = service as LocalService.LocalBinder
                playerService = binder.getService()
                isBound = true
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                Log.d("test123", "onServiceDisconnected")
                isBound = false
            }

        }
    }

    private fun configureBottomNavigation() {
        binding.bnvMain.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_media -> {
                    if (supportFragmentManager.findFragmentByTag(FragmentPicker.TAG) == null) {
                        supportFragmentManager.commit {
                            setReorderingAllowed(true)
                            add(binding.flContainer.id, FragmentPicker())
                            addToBackStack(FragmentPicker.TAG)
                        }
                    } else
                        supportFragmentManager.commit {
                            detach(supportFragmentManager.fragments.last())
                            attach(supportFragmentManager.findFragmentByTag(FragmentPicker.TAG)!!)
                        }
                }
                R.id.navigation_music -> {
                    supportFragmentManager.commit {
                        setReorderingAllowed(true)
                        add(binding.flContainer.id, FragmentPodcastsCategories())
                        addToBackStack(null)
                    }
                }
                R.id.navigation_main -> {
                    supportFragmentManager.commit {
                        setReorderingAllowed(true)
                        add(binding.flContainer.id, FragmentPlayer())
                        addToBackStack(null)
                    }
                }
                R.id.navigation_article -> {
                    supportFragmentManager.commit {
                        setReorderingAllowed(true)
                        add(binding.flContainer.id, FragmentArticles())
                        addToBackStack(null)
                    }
                }
                R.id.navigation_search -> {
                    supportFragmentManager.commit {
                        setReorderingAllowed(true)
                        add(binding.flContainer.id, FragmentSearch())
                        addToBackStack(null)
                    }
                }
            }
            true
        }
    }

    fun startPlayer() {
//        val myService = Intent(this@MainActivity, PlayerService::class.java)
//        startService(myService)
//        bindService(myService, serviceConnection, BIND_AUTO_CREATE)
    }

    fun setAudioToPlayer() {
//        setDataSource(tracks[position].file)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (supportFragmentManager.backStackEntryCount > 0)
            supportFragmentManager.popBackStack()
    }
}