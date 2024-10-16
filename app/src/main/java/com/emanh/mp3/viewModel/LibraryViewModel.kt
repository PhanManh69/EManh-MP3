package com.emanh.mp3.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emanh.mp3.model.LibraryModel
import com.emanh.mp3.model.data.LibraryData

class LibraryViewModel : ViewModel() {

    private val _libraryHomeList = MutableLiveData<MutableList<LibraryModel>>()
    val libraryHomeList: LiveData<MutableList<LibraryModel>> get() = _libraryHomeList

    init {
        val libraryHomeData = LibraryData().listLibrary()
        _libraryHomeList.value = libraryHomeData
    }
}