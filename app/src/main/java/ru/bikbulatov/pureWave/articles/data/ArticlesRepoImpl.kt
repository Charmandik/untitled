package ru.bikbulatov.pureWave.articles.data

import ru.bikbulatov.pureWave.authors.data.AuthorsRepo

class ArticlesRepoImpl : AuthorsRepo {
    override suspend fun getArticles() {
        TODO("Not yet implemented")
    }

    override suspend fun getArticle(id: Int) {
        TODO("Not yet implemented")
    }
}