package ru.bikbulatov.pureWave.articles.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import ru.bikbulatov.pureWave.articles.data.ArticlesRepo
import ru.bikbulatov.pureWave.articles.data.ArticlesRepoImpl

@Module
@InstallIn(ActivityComponent::class)
object ArticlesModule {
    @Provides
    fun provideArticlesRepo(): ArticlesRepo {
        return ArticlesRepoImpl()
    }
}