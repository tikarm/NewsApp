package com.example.newsapp.domain.model

import com.google.gson.annotations.SerializedName

data class FullResponse(
    @SerializedName("response") val response: Response? = null,
)