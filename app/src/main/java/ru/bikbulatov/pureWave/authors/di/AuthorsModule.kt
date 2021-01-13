package ru.bikbulatov.pureWave.authors.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import ru.bikbulatov.pureWave.authors.data.AuthorsRepo
import ru.bikbulatov.pureWave.authors.data.AuthorsRepoImpl

@Module
@InstallIn(ActivityComponent::class)
object AuthorsModule {
    @Provides
    fun provideArticlesRepo(): AuthorsRepo {
        return AuthorsRepoImpl()
    }
}