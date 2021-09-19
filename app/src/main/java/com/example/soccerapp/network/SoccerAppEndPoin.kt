package com.example.soccerapp.network

import com.example.soccerapp.network.response.CountrieResponse
import com.example.soccerapp.network.response.LeaguesResponse
import com.example.soccerapp.network.response.TeamResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SoccerAppEndPoin {
    @GET("all_countries.php")
    suspend fun countrie(): Response<CountrieResponse>

    @GET("search_all_leagues.php")
    suspend fun leagues(
        @Query("c") city: String
    ):Response<LeaguesResponse>

    @GET("search_all_teams.php")
    suspend fun team(
        @Query("l") leagues: String
    ):Response<TeamResponse>

    @GET("searchteams.php")
    suspend fun searchTeam(
        @Query("t") team:String
    ):Response<TeamResponse>
}
