package com.example.soccerapp.ui.countrie

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
import com.example.soccerapp.databinding.FragmentCountrieBinding
import com.example.soccerapp.network.Resource
import com.example.soccerapp.network.response.CountrieResponse
import timber.log.Timber

class CountrieFragment : Fragment() {

    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(CountrieViewModel::class.java) }
    private lateinit var binding: FragmentCountrieBinding
    private lateinit var countrieAdapter: CountrieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentCountrieBinding.inflate(inflater, container, false)
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
        countrieAdapter = CountrieAdapter(arrayListOf(), object : CountrieAdapter.OnAdapterListener{
            override fun onClick(result: CountrieResponse.Country) {
                findNavController().navigate(
                    R.id.action_countrieFragment_to_leaguesFragment,
                    bundleOf("countrie_name" to result.nameEn)
                )
            }
        })
        binding.listLeagues.adapter = countrieAdapter
    }

    private fun setupView() {
        viewModel.titleBar.postValue("Cari Negara")
    }

    private fun setupObserver() {
        viewModel.coutrieResponse.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Loading->{
                    binding.refreshLeagues.isRefreshing = true
                    Timber.d("countrieResponse :: Loading")
                }
                is Resource.Success->{
                    binding.refreshLeagues.isRefreshing = false
                    Timber.d("countrieResponse :: ${it.data!!.countries}")
                    countrieAdapter.setData(it.data.countries)
                }
                is Resource.Error->{
                    binding.refreshLeagues.isRefreshing = false
                    Timber.d("countrieResponse :: Error = ${it.message}")
                }
            }
        })
    }

    private fun setupListener() {
        binding.editSearch.doAfterTextChanged {
            countrieAdapter.filter.filter(it.toString())
        }
        binding.refreshLeagues.setOnRefreshListener {
            viewModel.fetchCountrie()
        }
    }
}