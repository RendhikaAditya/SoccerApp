package com.example.soccerapp.network.response
import com.google.gson.annotations.SerializedName

data class CountrieResponse(
    val countries: List<Country>
) {
    data class Country(
        @SerializedName("name_en")
        val nameEn: String
    )
}