package com.example.ishank_mediaplayer.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.ishank_mediaplayer.model.AllCategoryResponse
import com.example.ishank_mediaplayer.model.AudioTrackResponse
import com.example.ishank_mediaplayer.model.CatIdBody
import com.example.ishank_mediaplayer.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object AudioTrackRepository {
    val audioTrackResponse = MutableLiveData<AudioTrackResponse>()

    fun getAllCategoryApiCall(catIdBody: CatIdBody): MutableLiveData<AudioTrackResponse> {

        val call = RetrofitClient.apiInterface.getAudioTack(catIdBody)


        call.enqueue(object : Callback<AudioTrackResponse> {
            override fun onFailure(call: Call<AudioTrackResponse>, t: Throwable) {
                // TODO("Not yet implemented")
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<AudioTrackResponse>,
                response: Response<AudioTrackResponse>
            ) {
                // TODO("Not yet implemented")
                Log.v("DEBUG : ", response.body().toString())

                val data = response.body()
                audioTrackResponse.postValue(data)
            }

        })

        return audioTrackResponse
    }

}