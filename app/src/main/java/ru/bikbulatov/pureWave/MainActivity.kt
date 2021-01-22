package ru.bikbulatov.pureWave

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import dagger.hilt.android.AndroidEntryPoint
import ru.bikbulatov.pureWave.articles.ui.FragmentArticle
import ru.bikbulatov.pureWave.databinding.ActivityMainBinding
import ru.bikbulatov.pureWave.mainWindow.FragmentPicker
import ru.bikbulatov.pureWave.podcasts.FragmentPodcasts

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureBottomNavigation()
    }

    private fun configureBottomNavigation() {
        binding.bnvMain.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_media -> {
                    supportFragmentManager.commit {
                        setReorderingAllowed(true)
                        replace(
                            binding.flContainer.id,
                            FragmentPicker()
                        ) //its true position of picker
                    }
                }
                R.id.navigation_music -> {
                    supportFragmentManager.commit {
                        setReorderingAllowed(true)
                        add(binding.flContainer.id, FragmentPodcasts())
//                        add(binding.flContainer.id, FragmentPicker())
                    }
                }
                R.id.navigation_main -> {
                    supportFragmentManager.commit {
                        setReorderingAllowed(true)
                        add(binding.flContainer.id, FragmentPlayer())
                    }
                }
                R.id.navigation_article -> {
                    supportFragmentManager.commit {
                        setReorderingAllowed(true)
                        add(binding.flContainer.id, FragmentArticle())
                    }
                }
                R.id.navigation_search -> {
                    supportFragmentManager.commit {
                        setReorderingAllowed(true)
                        add(binding.flContainer.id, FragmentSearch())
                    }
                }
            }
            true
        }

    }
}