package ru.bikbulatov.pureWave.data

import retrofit2.http.GET
import retrofit2.http.Path
import ru.bikbulatov.pureWave.settings.VacanciesModel

interface UtilsApi {
    @GET("vacancies")
    suspend fun getVacancies(): List<VacanciesModel>


    //todo model
    @GET("contact")
    suspend fun getContactInfo(): List<VacanciesModel>

    //todo model
    @GET("team")
    suspend fun getTeamList(): List<VacanciesModel>

    //todo model
    @GET("team/{teamId}")
    suspend fun getTeamList(
        @Path("teamId") teamId: Int
    ): VacanciesModel
}