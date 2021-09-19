package com.example.soccerapp.ui.liga

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.soccerapp.R
import com.example.soccerapp.databinding.FragmentLigaBinding
import com.example.soccerapp.ui.countrie.CountrieActivity
import com.example.soccerapp.ui.searchtim.SearchTimActivity
import com.squareup.picasso.Picasso
import timber.log.Timber

class LigaFragment : Fragment(){

    private lateinit var binding: FragmentLigaBinding
    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(LigaViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentLigaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
        setupObserve()
    }

    private fun setupObserve() {
        viewModel.preferences.observe(viewLifecycleOwner, {pref->
            pref.forEach {
                Timber.d("PrefIdTim :: $it")
                binding.txtNameClub.setText(it.nameTeam)
                binding.txtManagerClub.setText(it.manager)
                binding.txtDescriptionClub.setText(it.description)
                Picasso.with(context).load(it.teamFoto).error(R.drawable.ic_broken_image).into(binding.imgLogo)
                Picasso.with(context).load(it.teamStadium).error(R.drawable.ic_broken_image).into(binding.imgStadium)
            }
        })
    }

    private fun setupListener() {
        binding.editCountrie.setOnClickListener {
            startActivity(Intent(context, CountrieActivity::class.java))
        }
        binding.buttonCariTim.setOnClickListener {
            startActivity(Intent(context, SearchTimActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.getPreferences()
        loadingCost(false)
    }

    private fun loadingCost(loading: Boolean) {
        if (loading) binding.progressLiga.visibility = View.VISIBLE
        else binding.progressLiga.visibility = View.GONE
    }

}