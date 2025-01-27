package com.example.audiobook.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Podcasts(
  @SerializedName("id")
    var id: String? = null,
  @SerializedName("rss")
    var rss: String? = null,
  @SerializedName("type") var type: String? = null,
  @SerializedName("email") var email: String? = null,
  @SerializedName("extra") var extra: Extra? = Extra(),
  @SerializedName("image") var image: String? = null,
  @SerializedName("title") var title: String? = null,
  @SerializedName("country") var country: String? = null,
  @SerializedName("website") var website: String? = null,
  @SerializedName("language") var language: String? = null,
  @SerializedName("genre_ids") var genreIds: ArrayList<Int> = arrayListOf(),
  @SerializedName("itunes_id") var itunesId: Long? = null,
  @SerializedName("publisher") var publisher: String? = null,
  @SerializedName("thumbnail") var thumbnail: String? = null,
  @SerializedName("is_claimed") var isClaimed: Boolean? = null,
  @SerializedName("description") var description: String? = null,
  @SerializedName("looking_for") var lookingFor: LookingFor? = LookingFor(),
  @SerializedName("has_sponsors") var hasSponsors: Boolean? = null,
  @SerializedName("listen_score") var listenScore: Long? = null,
  @SerializedName("total_episodes") var totalEpisodes: Long? = null,
  @SerializedName("listennotes_url") var listennotesUrl: String? = null,
  @SerializedName("audio_length_sec") var audioLengthSec: Long? = null,
  @SerializedName("explicit_content") var explicitContent: Boolean? = null,
  @SerializedName("latest_episode_id") var latestEpisodeId: String? = null,
  @SerializedName("latest_pub_date_ms") var latestPubDateMs: Long? = null,
  @SerializedName("earliest_pub_date_ms") var earliestPubDateMs: Long? = null,
  @SerializedName("has_guest_interviews") var hasGuestInterviews: Boolean? = null,
  @SerializedName("update_frequency_hours") var updateFrequencyHours: Long? = null,
  @SerializedName("listen_score_global_rank") var listenScoreGlobalRank: String? = null
): Parcelable {
  constructor(parcel: Parcel) : this(
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    TODO("extra"),
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    TODO("genreIds"),
    parcel.readValue(Long::class.java.classLoader) as? Long,
    parcel.readString(),
    parcel.readString(),
    parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
    parcel.readString(),
    TODO("lookingFor"),
    parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
    parcel.readValue(Long::class.java.classLoader) as? Long,
    parcel.readValue(Long::class.java.classLoader) as? Long,
    parcel.readString(),
    parcel.readValue(Long::class.java.classLoader) as? Long,
    parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
    parcel.readString(),
    parcel.readValue(Long::class.java.classLoader) as? Long,
    parcel.readValue(Long::class.java.classLoader) as? Long,
    parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
    parcel.readValue(Long::class.java.classLoader) as? Long,
    parcel.readString()
  )
  companion object CREATOR : Parcelable.Creator<Podcasts> {
    override fun createFromParcel(parcel: Parcel): Podcasts {
      return Podcasts(parcel)
    }

    override fun newArray(size: Int): Array<Podcasts?> {
      return arrayOfNulls(size)
    }
  }

  override fun describeContents(): Int {
    return 0
  }

  override fun writeToParcel(dest: Parcel, flags: Int) {
    TODO("Not yet implemented")
  }
}