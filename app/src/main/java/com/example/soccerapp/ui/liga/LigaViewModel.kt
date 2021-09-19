package com.example.soccerapp.ui.liga

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soccerapp.database.preferences.PreferencesModel
import com.example.soccerapp.network.ApiService
import com.example.soccerapp.network.Resource
import com.example.soccerapp.network.SoccerAppEndPoin
import com.example.soccerapp.network.SoccerAppRepository
import com.example.soccerapp.network.response.CountrieResponse
import com.example.soccerapp.network.response.LeaguesResponse
import com.example.soccerapp.network.response.TeamResponse
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception

class LigaViewModel(
    val repository: SoccerAppRepository
) : ViewModel() {
    val preferences: MutableLiveData<List<PreferencesModel>> = MutableLiveData()

    fun getPreferences(){
        preferences.value = repository.getPreferences()
    }
}