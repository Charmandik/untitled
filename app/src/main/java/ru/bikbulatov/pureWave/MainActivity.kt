package ru.bikbulatov.pureWave

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.bikbulatov.pureWave.databinding.ActivityMainBinding

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
                    supportFragmentManager
                        .beginTransaction()
                        .replace(binding.flContainer.id, FragmentPicker())
                        .commit()
                }
                R.id.navigation_music -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(binding.flContainer.id, FragmentProgramsCategories())
                        .commit()
                }
                R.id.navigation_main -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(binding.flContainer.id, FragmentPlayer())
                        .commit()
                }
                R.id.navigation_article -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(binding.flContainer.id, FragmentArticle())
                        .commit()
                }
                R.id.navigation_search -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(binding.flContainer.id, FragmentSearch())
                        .commit()
                }
            }
            true
        }

    }
}