package com.example.audiobook.model

import com.google.gson.annotations.SerializedName


data class PodcastDetails(

  @SerializedName("id") var id: String? = null,
  @SerializedName("rss") var rss: String? = null,
  @SerializedName("type") var type: String? = null,
  @SerializedName("email") var email: String? = null,
  @SerializedName("extra") var extra: Extra? = Extra(),
  @SerializedName("image") var image: String? = null,
  @SerializedName("title") var title: String? = null,
  @SerializedName("country") var country: String? = null,
  @SerializedName("website") var website: String? = null,
  @SerializedName("episodes") var episodes: ArrayList<Episodes> = arrayListOf(),
  @SerializedName("language") var language: String? = null,
  @SerializedName("genre_ids") var genreIds: ArrayList<Int> = arrayListOf(),
  @SerializedName("itunes_id") var itunesId: Int? = null,
  @SerializedName("publisher") var publisher: String? = null,
  @SerializedName("thumbnail") var thumbnail: String? = null,
  @SerializedName("is_claimed") var isClaimed: Boolean? = null,
  @SerializedName("description") var description: String? = null,
  @SerializedName("looking_for") var lookingFor: LookingFor? = LookingFor(),
  @SerializedName("has_sponsors") var hasSponsors: Boolean? = null,
  @SerializedName("listen_score") var listenScore: Int? = null,
  @SerializedName("total_episodes") var totalEpisodes: Int? = null,
  @SerializedName("listennotes_url") var listennotesUrl: String? = null,
  @SerializedName("audio_length_sec") var audioLengthSec: Int? = null,
  @SerializedName("explicit_content") var explicitContent: Boolean? = null,
  @SerializedName("latest_episode_id") var latestEpisodeId: String? = null,
  @SerializedName("latest_pub_date_ms") var latestPubDateMs: Int? = null,
  @SerializedName("earliest_pub_date_ms") var earliestPubDateMs: Int? = null,
  @SerializedName("has_guest_interviews") var hasGuestInterviews: Boolean? = null,
  @SerializedName("next_episode_pub_date") var nextEpisodePubDate: Int? = null,
  @SerializedName("update_frequency_hours") var updateFrequencyHours: Int? = null,
  @SerializedName("listen_score_global_rank") var listenScoreGlobalRank: String? = null

)