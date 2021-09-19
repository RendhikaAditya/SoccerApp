package com.example.soccerapp.ui.countrie

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.soccerapp.databinding.AdapterCountrieBinding
import com.example.soccerapp.network.response.CountrieResponse
import timber.log.Timber

class CountrieAdapter(
    var countries: ArrayList<CountrieResponse.Country>,
    var listener: OnAdapterListener
): RecyclerView.Adapter<CountrieAdapter.ViewHolder>()
    , Filterable
{

    private var countrieFilter = ArrayList<CountrieResponse.Country>()

    init {
        countrieFilter = countries
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        AdapterCountrieBinding.inflate(LayoutInflater.from(parent.context),parent, false)
    )

    override fun onBindViewHolder(holder: CountrieAdapter.ViewHolder, position: Int) {
        val countrie = countrieFilter[position]
        holder.binding.textName.text = countrie.nameEn
        holder.binding.container.setOnClickListener {
            listener.onClick(countrie)
        }
    }

    override fun getItemCount() = countrieFilter.size

    class ViewHolder(val binding: AdapterCountrieBinding): RecyclerView.ViewHolder(binding.root )

    interface OnAdapterListener{
        fun onClick(result: CountrieResponse.Country)
    }

    fun setData(data : List<CountrieResponse.Country>){
        countries.clear()
        countries.addAll(data)
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                Timber.d("charSearch: $charSearch")
                if (charSearch.isEmpty()) {
                    countrieFilter = countries
                } else {
                    val citiesFiltered = ArrayList<CountrieResponse.Country>()
                    for (countrie in countries) {
                        if (countrie.nameEn.toLowerCase().contains(charSearch.toLowerCase())) {
                            citiesFiltered.add(countrie)
                        }
                    }
                    countrieFilter = citiesFiltered
                }
                val citiesFilteredResult = FilterResults()
                citiesFilteredResult.values = countrieFilter
                return citiesFilteredResult
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                countrieFilter = results?.values as ArrayList<CountrieResponse.Country>
                notifyDataSetChanged()
            }

        }
    }
}