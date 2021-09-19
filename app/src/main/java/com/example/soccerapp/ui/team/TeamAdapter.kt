package com.example.soccerapp.ui.leagues

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.soccerapp.databinding.AdapterCountrieBinding
import com.example.soccerapp.network.response.LeaguesResponse
import com.example.soccerapp.network.response.TeamResponse
import timber.log.Timber

class TeamAdapter(
    var teams: ArrayList<TeamResponse.Team>,
    var listener: OnAdapterListener
): RecyclerView.Adapter<TeamAdapter.ViewHolder>()
    , Filterable
{

    private var teamFilter = ArrayList<TeamResponse.Team>()

    init {
        teamFilter = teams
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        AdapterCountrieBinding.inflate(LayoutInflater.from(parent.context),parent, false)
    )

    override fun onBindViewHolder(holder: TeamAdapter.ViewHolder, position: Int) {
        val team = teamFilter[position]
        holder.binding.textName.text = team.strTeam
        holder.binding.container.setOnClickListener {
            listener.onClick(team)
        }
    }

    override fun getItemCount() = teamFilter.size

    class ViewHolder(val binding: AdapterCountrieBinding): RecyclerView.ViewHolder(binding.root )

    interface OnAdapterListener{
        fun onClick(result: TeamResponse.Team)
    }

    fun setData(data : List<TeamResponse.Team>){
        teams.clear()
        teams.addAll(data)
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                Timber.d("charSearch: $charSearch")
                if (charSearch.isEmpty()) {
                    teamFilter = teams
                } else {
                    val citiesFiltered = ArrayList<TeamResponse.Team>()
                    for (tim in teams) {
                        if (tim.strTeam.toLowerCase().contains(charSearch.toLowerCase())) {
                            citiesFiltered.add(tim)
                        }
                    }
                    teamFilter = citiesFiltered
                }
                val citiesFilteredResult = FilterResults()
                citiesFilteredResult.values = teamFilter
                return citiesFilteredResult
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                teamFilter = results?.values as ArrayList<TeamResponse.Team>
                notifyDataSetChanged()
            }

        }
    }
}