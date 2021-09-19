package com.example.soccerapp.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val baseUrl = "https://www.thesportsdb.com/api/v1/json/1/"
//private const val apiKey = "a9ddbfa138c1cda3deeb97ea1ef126a2"

object ApiService {
    fun getClient(): SoccerAppEndPoin {

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .build()
                chain.proceed(request)
            }
            .build()

        val gson = GsonBuilder().serializeNulls().create()

        return Retrofit.Builder()
            .baseUrl( baseUrl )
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
            .create(SoccerAppEndPoin::class.java)
    }



}