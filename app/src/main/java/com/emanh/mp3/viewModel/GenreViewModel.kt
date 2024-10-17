package com.emanh.mp3.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emanh.mp3.model.GenreModel
import com.emanh.mp3.model.data.GenreData

class GenreViewModel : ViewModel() {

    private val _musicGenreList = MutableLiveData<MutableList<GenreModel>>()
    val musicGenreList: LiveData<MutableList<GenreModel>> get() = _musicGenreList

    init {
        val musicGenreData = GenreData().listGenre()
        _musicGenreList.value = musicGenreData
    }
}