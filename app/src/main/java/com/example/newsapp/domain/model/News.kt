package com.example.newsapp.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class News(
    @PrimaryKey @SerializedName("id") val id: String,
    @SerializedName("sectionId") val sectionID: String? = null,
    @SerializedName("sectionName") val sectionName: String? = null,
    @SerializedName("webPublicationDate") val webPublicationDate: String? = null,
    @SerializedName("webTitle") val webTitle: String? = null,
    @ColumnInfo(name = "is_favorite") var isFavorite: Boolean? = false
)