package com.example.ishank_mediaplayer.model
import com.google.gson.annotations.SerializedName


data class AudioTrackResponse(
    @SerializedName("data")
    val `data`: List<AudioTrackData>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)
