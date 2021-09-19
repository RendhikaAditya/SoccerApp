package com.example.soccerapp.ui.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.soccerapp.databinding.FragmentTeamBinding
import com.example.soccerapp.network.Resource
import com.example.soccerapp.network.response.TeamResponse
import com.example.soccerapp.ui.countrie.CountrieViewModel
import com.example.soccerapp.ui.leagues.TeamAdapter
import timber.log.Timber

class TeamFragment : Fragment() {

    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(CountrieViewModel::class.java) }
    private val leaguesName by lazy { requireArguments().getString("leagues_name") }
    private lateinit var binding: FragmentTeamBinding
    private lateinit var teamAdapter: TeamAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTeamBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupObserver()
        setupListener()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        teamAdapter = TeamAdapter(arrayListOf(), object : TeamAdapter.OnAdapterListener{
            override fun onClick(result: TeamResponse.Team) {
                Toast.makeText(context, "${result.strTeam}", Toast.LENGTH_SHORT).show()
                viewModel.savePref(
                    result.idTeam,
                    result.strTeam,
                    result.strDescriptionEN,
                    result.strCountry,
                    result.strManager,
                    if(result.strStadiumThumb.isNullOrEmpty()){"null"}else{result.strStadiumThumb},
                    if(result.strTeamBadge.isNullOrEmpty()){"null"}else{result.strTeamBadge}
                )
                requireActivity().finish()
            }
        })
        binding.listLeagues.adapter = teamAdapter
    }

    private fun setupListener() {
        binding.editSearch.doAfterTextChanged {
            teamAdapter.filter.filter(it.toString())
        }
        viewModel.fetchTeam(leaguesName!!)
        binding.refreshLeagues.setOnRefreshListener {
            viewModel.fetchTeam(leaguesName!!)
        }
    }

    private fun setupObserver() {
        viewModel.teamResponse.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Loading->{
                    binding.refreshLeagues.isRefreshing = true
                    Timber.d("leaguesResponse :: Loading")
                }
                is Resource.Success->{
                    binding.refreshLeagues.isRefreshing = false
                    Timber.d("leaguesResponse :: ${it.data!!.teams}")
                    teamAdapter.setData(it.data.teams)
                }
                is Resource.Error->{
                    binding.refreshLeagues.isRefreshing = false
                    Timber.d("leaguesResponse :: Error = ${it.message}")
                }
            }
        })
    }

    private fun setupView() {
        viewModel.titleBar.postValue("Pilih Tim")
    }

}