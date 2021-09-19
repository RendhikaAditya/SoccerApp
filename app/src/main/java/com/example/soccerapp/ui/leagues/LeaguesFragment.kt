package com.example.soccerapp.ui.leagues

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.soccerapp.R
import com.example.soccerapp.databinding.FragmentLeaguesBinding
import com.example.soccerapp.network.Resource
import com.example.soccerapp.network.response.LeaguesResponse
import com.example.soccerapp.ui.countrie.CountrieViewModel
import timber.log.Timber

class LeaguesFragment : Fragment() {

    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(CountrieViewModel::class.java) }
    private val countrieName by lazy { requireArguments().getString("countrie_name") }
    private lateinit var binding: FragmentLeaguesBinding
    private lateinit var leaguesAdapter: LeaguesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLeaguesBinding.inflate(inflater, container, false)
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
        leaguesAdapter = LeaguesAdapter(arrayListOf(), object : LeaguesAdapter.OnAdapterListener{
            override fun onClick(result: LeaguesResponse.Country) {
                findNavController().navigate(
                    R.id.action_leaguesFragment_to_teamFragment,
                    bundleOf("leagues_name" to result.strLeague)
                )
            }
        })
        binding.listLeagues.adapter = leaguesAdapter
    }

    private fun setupListener() {
        binding.editSearch.doAfterTextChanged {
            leaguesAdapter.filter.filter(it.toString())
        }
        viewModel.fetchLeagues(countrieName!!)
        binding.refreshLeagues.setOnRefreshListener {
            viewModel.fetchLeagues(countrieName!!)
        }
    }

    private fun setupObserver() {
        viewModel.leaguesResponse.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Loading->{
                    binding.refreshLeagues.isRefreshing = true
                    Timber.d("leaguesResponse :: Loading")
                }
                is Resource.Success->{
                    binding.refreshLeagues.isRefreshing = false
                    Timber.d("leaguesResponse :: ${it.data!!.countrys}")
                    leaguesAdapter.setData(it.data.countrys)
                }
                is Resource.Error->{
                    binding.refreshLeagues.isRefreshing = false
                    Timber.d("leaguesResponse :: Error = ${it.message}")
                }
            }
        })
    }

    private fun setupView() {
        viewModel.titleBar.postValue("Pilih Liga")
    }

}