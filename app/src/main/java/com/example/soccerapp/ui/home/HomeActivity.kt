package com.example.soccerapp.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.soccerapp.database.preferences.SoccerAppPreferences
import com.example.soccerapp.databinding.ActivityHomeBinding
import com.example.soccerapp.network.ApiService
import com.example.soccerapp.network.SoccerAppRepository
import com.example.soccerapp.ui.liga.LigaViewModel
import com.example.soccerapp.ui.liga.LigaViewModelFactory
import com.google.android.material.tabs.TabLayoutMediator

class HomeActivity : AppCompatActivity() {
    private val api by lazy { ApiService.getClient() }
    private val pref by lazy { SoccerAppPreferences(this) }
    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: LigaViewModel
    private lateinit var repository: SoccerAppRepository
    private lateinit var viewModelFactory: LigaViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupTab()
        setupViewModel()
    }

    private fun setupViewModel() {
        repository = SoccerAppRepository(api, pref)
        viewModelFactory = LigaViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(LigaViewModel::class.java)
    }


    private fun setupTab() {
        val tabTitle =  arrayOf("Tim")
        val tabAdapter = HomeTabAdapter(supportFragmentManager, lifecycle)
        binding.viewPager.adapter = tabAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager){ tab, position->
            tab.text = tabTitle[position]
        }.attach()
    }
}