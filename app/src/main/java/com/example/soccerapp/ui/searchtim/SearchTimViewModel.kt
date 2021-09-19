package com.example.soccerapp.ui.searchtim

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soccerapp.database.preferences.PreferencesModel
import com.example.soccerapp.network.Resource
import com.example.soccerapp.network.SoccerAppRepository
import com.example.soccerapp.network.response.CountrieResponse
import com.example.soccerapp.network.response.LeaguesResponse
import com.example.soccerapp.network.response.TeamResponse
import kotlinx.coroutines.launch

class SearchTimViewModel(
    val repository: SoccerAppRepository
) : ViewModel() {
    val titleBar: MutableLiveData<String> = MutableLiveData("")
    val preferences: MutableLiveData<List<PreferencesModel>> = MutableLiveData()
    val teamResponse: MutableLiveData<Resource<TeamResponse>> = MutableLiveData()

    fun fetchTeam(leagues: String) = viewModelScope.launch {
        teamResponse.value = Resource.Loading()
        try {
            val response = repository.fetchSearchTeam(leagues)
            teamResponse.value = Resource.Success(response.body()!!)
        } catch (e: Exception) {
            teamResponse.value = Resource.Error(e.message.toString())
        }
    }

    fun savePref(
        idTeam: String,
        nameTeam: String,
        description:String,
        country:String,
        manager:String,
        teamStadium:String,
        teamFoto:String
    ) {
        repository.savePreferences(idTeam, nameTeam, description, country, manager, teamStadium, teamFoto)
    }
}