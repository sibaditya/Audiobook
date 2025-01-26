package com.example.audiobook.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    // Define the base URL for the API
    private const val BASE_URL = "https://listen-api-test.listennotes.com/api/v2/"

    // Create a Retrofit instance using lazy initialization
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Define a property to access the PodcastService interface
    val podcastService: ApiService by lazy {
        // Create an instance of the PodcastService interface using Retrofit
        retrofit.create(ApiService::class.java)
    }
}