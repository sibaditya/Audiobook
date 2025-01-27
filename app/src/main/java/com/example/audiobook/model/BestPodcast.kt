package com.example.audiobook.model

import com.google.gson.annotations.SerializedName


data class BestPodcast(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("total") var total: Int? = null,
    @SerializedName("has_next") var hasNext: Boolean? = null,
    @SerializedName("podcasts") var podcasts: ArrayList<Podcasts> = arrayListOf(),
    @SerializedName("parent_id") var parentId: Int? = null,
    @SerializedName("page_number") var pageNumber: Int? = null,
    @SerializedName("has_previous") var hasPrevious: Boolean? = null,
    @SerializedName("listennotes_url") var listennotesUrl: String? = null,
    @SerializedName("next_page_number") var nextPageNumber: Int? = null,
    @SerializedName("previous_page_number") var previousPageNumber: Int? = null
)