package com.example.audiobook.api

import com.example.audiobook.model.BestPodcast
import com.example.audiobook.model.PodcastDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("best_podcasts")
    fun getBestPodcasts(): Call<BestPodcast>

    @GET("podcasts/{id}")
    fun getBestPodcastsInfo(
        @Path("id") id: String
    ): Call<PodcastDetails>
}