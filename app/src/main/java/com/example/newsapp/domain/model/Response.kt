package com.example.newsapp.domain.model

import com.google.gson.annotations.SerializedName

data class Response (
    @SerializedName ("status") val status: String? = null,
    @SerializedName ("userTier") val userTier: String? = null,
    @SerializedName ("total") val total: Long? = null,
    @SerializedName ("startIndex") val startIndex: Long? = null,
    @SerializedName ("pageSize") val pageSize: Long? = null,
    @SerializedName ("currentPage") val currentPage: Long? = null,
    @SerializedName ("pages") val pages: Long? = null,
    @SerializedName ("orderBy") val orderBy: String? = null,
    @SerializedName ("results") val news: List<News>? = null
)