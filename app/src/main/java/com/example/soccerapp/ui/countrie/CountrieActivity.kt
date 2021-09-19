package com.example.soccerapp.ui.countrie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.soccerapp.R
import com.example.soccerapp.database.preferences.SoccerAppPreferences
import com.example.soccerapp.network.ApiService
import com.example.soccerapp.network.SoccerAppRepository

class CountrieActivity : AppCompatActivity(){

    private val api by lazy { ApiService.getClient() }
    private val pref by lazy { SoccerAppPreferences(this) }
    private lateinit var viewModel: CountrieViewModel
    private lateinit var repository: SoccerAppRepository
    private lateinit var viewModelFactory: CountrieViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countrie)
        setupViewModel()
//        setupListener()
        setupObserver()
    }

    private fun setupViewModel() {
        repository = SoccerAppRepository(api, pref)
        viewModelFactory = CountrieViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CountrieViewModel::class.java)
    }

    private fun setupObserver() {
        viewModel.titleBar.observe(this, Observer {
            supportActionBar!!.title = it
        })
    }
//
//    private fun setupListener() {
//        TODO("Not yet implemented")
//    }


}