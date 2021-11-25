package com.example.ishank_mediaplayer.model

import com.google.gson.annotations.SerializedName



data class AudioTrackData(
    @SerializedName("audioName")
    val audioName: String,
    @SerializedName("audioTrackId")
    val audioTrackId: String,
    @SerializedName("catId")
    val catId: String,
    @SerializedName("catImage")
    val catImage: String,
    @SerializedName("catName")
    val catName: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("title")
    val title: String,
    val isPlaying:Boolean=false
)