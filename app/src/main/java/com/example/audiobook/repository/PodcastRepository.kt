package com.example.audiobook.repository

import androidx.lifecycle.MutableLiveData
import com.example.audiobook.api.ApiResponse
import com.example.audiobook.api.ApiService
import com.example.audiobook.model.BestPodcast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PodcastRepository @Inject constructor (private val apiService: ApiService) {

    // Create an instance of the podcast service from Retrofit
    private val _bestPodcastLiveData = MutableLiveData<ApiResponse<BestPodcast>>()
    val bestPodcastLiveData: MutableLiveData<ApiResponse<BestPodcast>>
        get() {
            return _bestPodcastLiveData
        }

    // Function to fetch Best Podcast
    fun getBestPodcast() {
        _bestPodcastLiveData.postValue(ApiResponse.ApiLoading())
        try {
            apiService.getBestPodcasts().enqueue(object : Callback<BestPodcast?> {
                override fun onResponse(
                    call: Call<BestPodcast?>,
                    response: Response<BestPodcast?>
                ) {
                    _bestPodcastLiveData.postValue(ApiResponse.ApiSuccess(response.body()))
                }

                override fun onFailure(call: Call<BestPodcast?>, t: Throwable) {
                    _bestPodcastLiveData.postValue(ApiResponse.ApiError(t.localizedMessage))
                }
            })
        } catch (e: Exception) {
            _bestPodcastLiveData.postValue(ApiResponse.ApiError(e.localizedMessage))
        }
    }
}
