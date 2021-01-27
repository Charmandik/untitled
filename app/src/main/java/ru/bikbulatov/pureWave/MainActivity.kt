package ru.bikbulatov.pureWave

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import dagger.hilt.android.AndroidEntryPoint
import ru.bikbulatov.pureWave.articles.ui.FragmentArticles
import ru.bikbulatov.pureWave.databinding.ActivityMainBinding
import ru.bikbulatov.pureWave.mainWindow.FragmentPicker
import ru.bikbulatov.pureWave.podcasts.ui.FragmentPodcasts

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureBottomNavigation()
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(
                binding.flContainer.id,
                FragmentPicker()
            ) //its true position of picker
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
                        add(binding.flContainer.id, FragmentPodcasts())
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

    override fun onBackPressed() {
        super.onBackPressed()
        if (supportFragmentManager.backStackEntryCount > 0)
            supportFragmentManager.popBackStack()
    }
}