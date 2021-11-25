package com.example.ishank_mediaplayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ishank_mediaplayer.model.AllCategoryResponse
import com.example.ishank_mediaplayer.repository.AllCategoryRepository

class MainActivityViewModel : ViewModel() {
    var allCategoryLiveData: MutableLiveData<AllCategoryResponse>? = null

    fun getAllCategory(): LiveData<AllCategoryResponse>? {
        allCategoryLiveData = AllCategoryRepository.getAllCategoryApiCall()
        return allCategoryLiveData
    }
}