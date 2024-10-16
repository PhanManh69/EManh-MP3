package com.emanh.mp3.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emanh.mp3.model.SongModel
import com.emanh.mp3.model.data.SongData

class SongViewModel : ViewModel() {

    private val _listenAgainList = MutableLiveData<MutableList<SongModel>>()
    val listenAgainList: LiveData<MutableList<SongModel>> get() = _listenAgainList

    private val _quickPicksList = MutableLiveData<MutableList<SongModel>>()
    val quickPicksList: LiveData<MutableList<SongModel>> get() = _quickPicksList

    private val _trendingList = MutableLiveData<MutableList<SongModel>>()
    val trendingList: LiveData<MutableList<SongModel>> get() = _trendingList

    init {
        val listenAgainData = SongData().listSong()
        _listenAgainList.value = listenAgainData

        val quickPicksData = SongData().listSong()
        _quickPicksList.value = quickPicksData

        val trendingData = SongData().listSong()
        _trendingList.value = trendingData
    }
}