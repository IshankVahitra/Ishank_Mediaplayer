package com.example.ishank_mediaplayer.model

import com.google.gson.annotations.SerializedName

data class AllCategoryData(
    @SerializedName("catId")
    val catId: String,
    @SerializedName("catImg")
    val catImg: String,
    @SerializedName("catName")
    val catName: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("modifiedAt")
    val modifiedAt: String,
    @SerializedName("status")
    val status: String
)