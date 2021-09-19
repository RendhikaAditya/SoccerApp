package com.example.soccerapp.ui.countrie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.soccerapp.network.SoccerAppRepository

class CountrieViewModelFactory(
    private val repository: SoccerAppRepository
): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CountrieViewModel(repository) as T
    }
}