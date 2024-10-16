package com.emanh.mp3.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emanh.mp3.model.ChannelModel
import com.emanh.mp3.model.data.ChannelData

class ChannelViewModel : ViewModel() {

    private val _channelList = MutableLiveData<MutableList<ChannelModel>>()
    val channelList: LiveData<MutableList<ChannelModel>> get() = _channelList

    init {
        val channelData = ChannelData().listChannel()
        _channelList.value = channelData
    }
}