package com.inforcap.exampleapiresttmdb.network

import com.inforcap.exampleapiresttmdb.core.Constants
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    // Lazy initialization of the ApiService
    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL) // Asegúrate de que esta URL termina con /
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create())) // Crea un convertidor Gson
            .build()
            .create(ApiService::class.java) // Crea la instancia de ApiService
    }
}