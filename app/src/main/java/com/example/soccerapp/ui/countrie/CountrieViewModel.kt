package com.example.soccerapp.ui.countrie

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

class CountrieViewModel(
    val repository: SoccerAppRepository
) : ViewModel() {
    val titleBar: MutableLiveData<String> = MutableLiveData("")
    val preferences: MutableLiveData<List<PreferencesModel>> = MutableLiveData()
    val coutrieResponse: MutableLiveData<Resource<CountrieResponse>> = MutableLiveData()
    val leaguesResponse: MutableLiveData<Resource<LeaguesResponse>> = MutableLiveData()
    val teamResponse: MutableLiveData<Resource<TeamResponse>> = MutableLiveData()


    init {
        fetchCountrie()
    }

    fun fetchCountrie() = viewModelScope.launch {
        coutrieResponse.value = Resource.Loading()
        try {
            val response = repository.fetchCity()
            coutrieResponse.value = Resource.Success(response.body()!!)
        } catch (e: Exception) {
            coutrieResponse.value = Resource.Error(e.message.toString())
        }
    }

    fun fetchLeagues(countrie: String) = viewModelScope.launch {
        leaguesResponse.value = Resource.Loading()
        try {
            val response = repository.fetchLeagues(countrie)
            leaguesResponse.value = Resource.Success(response.body()!!)
        } catch (e: Exception) {
            leaguesResponse.value = Resource.Error(e.message.toString())
        }
    }

    fun fetchTeam(leagues: String) = viewModelScope.launch {
        teamResponse.value = Resource.Loading()
        try {
            val response = repository.fetchTeam(leagues)
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