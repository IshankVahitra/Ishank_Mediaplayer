package com.example.ishank_mediaplayer.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.ishank_mediaplayer.model.AllCategoryResponse
import com.example.ishank_mediaplayer.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object AllCategoryRepository {
    val allCategorySetterGetter = MutableLiveData<AllCategoryResponse>()

    fun getAllCategoryApiCall(): MutableLiveData<AllCategoryResponse> {

        val call = RetrofitClient.apiInterface.getAllCategory()


        call.enqueue(object : Callback<AllCategoryResponse> {
            override fun onFailure(call: Call<AllCategoryResponse>, t: Throwable) {
                // TODO("Not yet implemented")
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<AllCategoryResponse>,
                response: Response<AllCategoryResponse>
            ) {
                // TODO("Not yet implemented")
                Log.v("DEBUG : ", response.body().toString())

                val data = response.body()
                allCategorySetterGetter.postValue(data)
            }
        })

        return allCategorySetterGetter
    }

}