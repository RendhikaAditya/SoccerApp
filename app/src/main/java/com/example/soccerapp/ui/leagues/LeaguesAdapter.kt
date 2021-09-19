package com.example.soccerapp.ui.leagues

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.soccerapp.databinding.AdapterCountrieBinding
import com.example.soccerapp.network.response.LeaguesResponse
import timber.log.Timber

class LeaguesAdapter(
    var leaguest: ArrayList<LeaguesResponse.Country>,
    var listener: OnAdapterListener
): RecyclerView.Adapter<LeaguesAdapter.ViewHolder>()
    , Filterable
{

    private var leaguesFilter = ArrayList<LeaguesResponse.Country>()

    init {
        leaguesFilter = leaguest
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        AdapterCountrieBinding.inflate(LayoutInflater.from(parent.context),parent, false)
    )

    override fun onBindViewHolder(holder: LeaguesAdapter.ViewHolder, position: Int) {
        val leagues = leaguesFilter[position]
        holder.binding.textName.text = leagues.strLeague
        holder.binding.container.setOnClickListener {
            listener.onClick(leagues)
        }
    }

    override fun getItemCount() = leaguesFilter.size

    class ViewHolder(val binding: AdapterCountrieBinding): RecyclerView.ViewHolder(binding.root )

    interface OnAdapterListener{
        fun onClick(result: LeaguesResponse.Country)
    }

    fun setData(data : List<LeaguesResponse.Country>){
        leaguest.clear()
        leaguest.addAll(data)
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                Timber.d("charSearch: $charSearch")
                if (charSearch.isEmpty()) {
                    leaguesFilter = leaguest
                } else {
                    val citiesFiltered = ArrayList<LeaguesResponse.Country>()
                    for (leagues in leaguest) {
                        if (leagues.strLeague.toLowerCase().contains(charSearch.toLowerCase())) {
                            citiesFiltered.add(leagues)
                        }
                    }
                    leaguesFilter = citiesFiltered
                }
                val citiesFilteredResult = FilterResults()
                citiesFilteredResult.values = leaguesFilter
                return citiesFilteredResult
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                leaguesFilter = results?.values as ArrayList<LeaguesResponse.Country>
                notifyDataSetChanged()
            }

        }
    }
}