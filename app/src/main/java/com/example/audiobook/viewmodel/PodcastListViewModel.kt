package com.example.audiobook.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.audiobook.api.ApiResponse
import com.example.audiobook.model.BestPodcast
import com.example.audiobook.repository.PodcastRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PodcastListViewModel @Inject constructor(private val podcastRepository: PodcastRepository): ViewModel() {

    // Expose an immutable LiveData for the UI to observe
    val bestPodcasts : MutableLiveData<ApiResponse<BestPodcast>>
        get() {
            return podcastRepository.bestPodcastLiveData
        }

    // Function to fetch best podcast data using coroutines
    fun fetchBestPodcasts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Use the repository to get podcast data asynchronously
                podcastRepository.getBestPodcast()
            } catch (e: Exception) {

            }
        }
    }
}
