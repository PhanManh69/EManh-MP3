package com.emanh.mp3.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emanh.mp3.model.ListenAgainModel
import com.emanh.mp3.model.data.ListenAgainData

class ListenAgainViewModel : ViewModel() {

    private val _listenAgainList = MutableLiveData<MutableList<ListenAgainModel>>()
    val listenAgainList: LiveData<MutableList<ListenAgainModel>> get() = _listenAgainList

    init {
        val listenAgainData = ListenAgainData().listenAgain()
        _listenAgainList.value = listenAgainData
    }
}