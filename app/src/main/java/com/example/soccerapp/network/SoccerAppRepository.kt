package com.example.soccerapp.network

import com.example.soccerapp.database.preferences.*

class SoccerAppRepository(
    private val api: SoccerAppEndPoin,
    private val pref: SoccerAppPreferences
) {
    suspend fun fetchCity()= api.countrie()

    suspend fun fetchLeagues(countrie: String)= api.leagues(countrie)

    suspend fun fetchTeam(leagues: String)= api.team(leagues)
    suspend fun fetchSearchTeam(team: String)= api.searchTeam(team)

    fun savePreferences(
        idTeam: String,
        nameTeam:String,
        description:String,
        country:String,
        manager:String,
        teamStadium:String,
        teamFoto:String
    ){
        pref.put(prefIdTeam, idTeam)
        pref.put(prefNameTeam, nameTeam)
        pref.put(prefDescription, description)
        pref.put(prefCountry, country)
        pref.put(prefManager, manager)
        pref.put(prefTeamStadium, teamStadium)
        pref.put(prefTeamFoto, teamFoto)
    }

    fun getPreferences(): List<PreferencesModel>{
        return listOf(PreferencesModel(
            idTeam = pref.getString(prefIdTeam),
            nameTeam = pref.getString(prefNameTeam),
            description = pref.getString(prefDescription),
            country = pref.getString(prefCountry),
            manager = pref.getString(prefManager),
            teamStadium = pref.getString(prefTeamStadium),
            teamFoto = pref.getString(prefTeamFoto)

        ))
    }
}