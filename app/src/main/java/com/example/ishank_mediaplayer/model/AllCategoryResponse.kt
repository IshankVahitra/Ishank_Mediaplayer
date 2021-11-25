package com.example.ishank_mediaplayer.model

import com.google.gson.annotations.SerializedName


data class AllCategoryResponse(
    @SerializedName("data")
    val `data`: List<AllCategoryData>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)

