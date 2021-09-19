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

class SearchTimAdapter(
    var teams: ArrayList<TeamResponse.Team>,
    var listener: OnAdapterListener
): RecyclerView.Adapter<SearchTimAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        AdapterCountrieBinding.inflate(LayoutInflater.from(parent.context),parent, false)
    )

    override fun onBindViewHolder(holder: SearchTimAdapter.ViewHolder, position: Int) {
        val team = teams[position]
        holder.binding.textName.text = team.strTeam
        holder.binding.container.setOnClickListener {
            listener.onClick(team)
        }
    }

    override fun getItemCount() = teams.size

    class ViewHolder(val binding: AdapterCountrieBinding): RecyclerView.ViewHolder(binding.root )

    interface OnAdapterListener{
        fun onClick(result: TeamResponse.Team)
    }

    fun setData(data : List<TeamResponse.Team>){
        teams.clear()
        teams.addAll(data)
        notifyDataSetChanged()
    }

}