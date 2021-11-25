package com.example.ishank_mediaplayer.retrofit

import com.example.ishank_mediaplayer.model.AllCategoryResponse
import com.example.ishank_mediaplayer.model.AudioTrackResponse
import com.example.ishank_mediaplayer.model.CatIdBody
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @GET("get_all_category")
    fun getAllCategory(): Call<AllCategoryResponse>

    @POST("audio_track")
    fun getAudioTack(@Body catId: CatIdBody): Call<AudioTrackResponse>

}