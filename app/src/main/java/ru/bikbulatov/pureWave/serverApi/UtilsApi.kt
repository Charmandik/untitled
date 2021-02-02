package ru.bikbulatov.pureWave.serverApi

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import ru.bikbulatov.pureWave.settings.VacanciesModel

interface UtilsApi {
    @GET("vacancies")
    suspend fun getVacancies(
        @Header("apikey") apiKey : String = Constants.API_KEY
    ): List<VacanciesModel>


    //todo model
    @GET("contact")
    suspend fun getContactInfo(
        @Header("apikey") apiKey : String = Constants.API_KEY
    ): List<VacanciesModel>

    //todo model
    @GET("team")
    suspend fun getTeamList(
        @Header("apikey") apiKey : String = Constants.API_KEY
    ): List<VacanciesModel>

    //todo model
    @GET("team/{teamId}")
    suspend fun getTeamList(
        @Header("apikey") apiKey : String = Constants.API_KEY,
        @Path("teamId") teamId: Int
    ): VacanciesModel
}