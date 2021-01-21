package ru.bikbulatov.pureWave.podcasts.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import ru.bikbulatov.pureWave.podcasts.data.PodcastsRepoImpl
import ru.bikbulatov.pureWave.podcasts.domain.PodcastsRepo

@Module
@InstallIn(ActivityComponent::class)
object PodcastModule {
    @Provides
    fun providePodcastRepo(): PodcastsRepo {
        return PodcastsRepoImpl()
    }
}