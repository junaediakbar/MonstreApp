package com.monstre.monstreapp.ui.camera

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CameraViewModel : ViewModel() {

    private val _isAlreadyTakePicture = MutableLiveData<Boolean>()
    val isAlreadyTakePicture: LiveData<Boolean> = _isAlreadyTakePicture

    init {
        _isAlreadyTakePicture.value =false
    }

    fun takePicture(state : Boolean){
        _isAlreadyTakePicture.value = state
    }
}