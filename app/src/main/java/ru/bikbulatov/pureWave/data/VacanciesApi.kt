package ru.bikbulatov.pureWave.data

import retrofit2.http.GET
import ru.bikbulatov.pureWave.VacanciesModel

interface VacanciesApi {
    @GET("vacancies")
    suspend fun getVacancies(): List<VacanciesModel>

}