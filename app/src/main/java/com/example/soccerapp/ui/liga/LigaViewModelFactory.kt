package com.example.soccerapp.ui.liga

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.soccerapp.network.ApiService
import com.example.soccerapp.network.SoccerAppEndPoin
import com.example.soccerapp.network.SoccerAppRepository
import java.util.concurrent.AbstractExecutorService

class LigaViewModelFactory(
    private val repository: SoccerAppRepository
): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LigaViewModel(repository) as T
    }
}