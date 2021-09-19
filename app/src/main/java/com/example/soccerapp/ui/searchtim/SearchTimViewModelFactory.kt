package com.example.soccerapp.ui.searchtim

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.soccerapp.network.SoccerAppRepository
import com.example.soccerapp.ui.searchtim.SearchTimViewModel

class SearchTimViewModelFactory(
    private val repository: SoccerAppRepository
): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchTimViewModel(repository) as T
    }
}