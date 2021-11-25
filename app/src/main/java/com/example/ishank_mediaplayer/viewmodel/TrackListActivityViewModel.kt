package com.example.ishank_mediaplayer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ishank_mediaplayer.model.AudioTrackResponse
import com.example.ishank_mediaplayer.model.CatIdBody
import com.example.ishank_mediaplayer.repository.AudioTrackRepository

class TrackListActivityViewModel : ViewModel() {
    var audioTrackLiveData: MutableLiveData<AudioTrackResponse>? = null

    fun getAudioTack(catIdBody: CatIdBody): LiveData<AudioTrackResponse>? {
        audioTrackLiveData = AudioTrackRepository.getAllCategoryApiCall(catIdBody)
        return audioTrackLiveData
    }
}