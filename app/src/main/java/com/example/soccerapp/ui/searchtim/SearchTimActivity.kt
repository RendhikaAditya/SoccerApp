package com.example.soccerapp.ui.searchtim

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.soccerapp.R
import com.example.soccerapp.database.preferences.SoccerAppPreferences
import com.example.soccerapp.network.ApiService
import com.example.soccerapp.network.SoccerAppRepository

class SearchTimActivity : AppCompatActivity(){

    private val api by lazy { ApiService.getClient() }
    private val pref by lazy { SoccerAppPreferences(this) }
    private lateinit var viewModel: SearchTimViewModel
    private lateinit var repository: SoccerAppRepository
    private lateinit var viewModelFactory: SearchTimViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_tim)
        setupViewModel()
        setupObserver()
    }

    private fun setupViewModel() {
        repository = SoccerAppRepository(api, pref)
        viewModelFactory = SearchTimViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SearchTimViewModel::class.java)
    }

    private fun setupObserver() {
        viewModel.titleBar.observe(this, Observer {
            supportActionBar!!.title = it
        })
    }


}