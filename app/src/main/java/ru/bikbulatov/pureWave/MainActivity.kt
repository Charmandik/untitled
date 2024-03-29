package ru.bikbulatov.pureWave

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.commit
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.AndroidEntryPoint
import ru.bikbulatov.pureWave.articles.ui.FragmentArticles
import ru.bikbulatov.pureWave.databinding.ActivityMainWithDrawerBinding
import ru.bikbulatov.pureWave.mainWindow.FragmentPicker
import ru.bikbulatov.pureWave.player.LocalService
import ru.bikbulatov.pureWave.podcasts.domain.models.TrackModel
import ru.bikbulatov.pureWave.podcasts.ui.FragmentPodcastsCategories


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    companion object {
        lateinit var instance: MainActivity
    }

    private lateinit var binding: ActivityMainWithDrawerBinding
    private lateinit var serviceConnection: ServiceConnection
    private lateinit var playerService: LocalService
    private var isBound = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainWithDrawerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        instance = this
        configureBottomNavigation()
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(
                binding.flContainer.id,
                FragmentPicker()
            )
        }
        serviceConnection = object : ServiceConnection {
            // Called when the connection with the service is established
            override fun onServiceConnected(className: ComponentName, service: IBinder) {
                // Because we have bound to an explicit
                // service that is running in our own process, we can
                // cast its IBinder to a concrete class and directly access it.
                val binder = service as LocalService.MyLocalBinder
                playerService = binder.getService()
                isBound = true
            }

            // Called when the connection with the service disconnects unexpectedly
            override fun onServiceDisconnected(className: ComponentName) {
                Log.e("TAG", "onServiceDisconnected")
                isBound = false
            }
        }
        startPlayer()
        binding.drawerLayout.openDrawer(GravityCompat.START)
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

    private fun configureDrawerMenu() {
        binding.tvMenuTeam.setOnClickListener{
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add(binding.flContainer.id, FragmentPodcastsCategories())
                addToBackStack(null)
            }
        }
        binding.tvMenuTeam.setOnClickListener{
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add(binding.flContainer.id, FragmentPodcastsCategories())
                addToBackStack(null)
            }
        }
        binding.tvMenuTeam.setOnClickListener{
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add(binding.flContainer.id, FragmentPodcastsCategories())
                addToBackStack(null)
            }
        }
        binding.tvMenuTeam.setOnClickListener{
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add(binding.flContainer.id, FragmentPodcastsCategories())
                addToBackStack(null)
            }
        }
    }

    fun startPlayer() {
        val intent = Intent(this, LocalService::class.java).also { intent ->
            bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        }
    }

    fun setAudioToPlayer(track: TrackModel) {
        playerService.setDataToPlayer(track)
    }

    fun setAudioToPlayer(trackList: List<TrackModel>) {
        playerService.setDataToPlayer(trackList)
    }

    fun getCurrentTrack(): MutableLiveData<TrackModel> {
        return playerService.currentTrack
    }

    fun pausePlayer() {
        playerService.pausePlayer()
    }

    fun turnDownVolume() {
        playerService.turnDownVolume()
    }

    fun turnUpVolume() {
        playerService.turnUpVolume()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (supportFragmentManager.backStackEntryCount > 0)
            supportFragmentManager.popBackStack()
    }
}