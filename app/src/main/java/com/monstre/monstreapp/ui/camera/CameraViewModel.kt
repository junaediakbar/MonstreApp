package com.monstre.monstreapp.ui.camera

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.monstre.monstreapp.data.MonstreRepository
import com.monstre.monstreapp.domain.model.User
import okhttp3.MultipartBody

class CameraViewModel(private val monstreRepository: MonstreRepository) : ViewModel() {

    private val _isAlreadyTakePicture = MutableLiveData<Boolean>()
    val isAlreadyTakePicture: LiveData<Boolean> = _isAlreadyTakePicture


    fun uploadImage (token : String, imageMultipart: MultipartBody.Part) = monstreRepository.updateImage("Bearer $token",imageMultipart)
    val user: LiveData<User> = monstreRepository.user.asLiveData()

    init {
        _isAlreadyTakePicture.value =false
    }

    fun takePicture(state : Boolean){
        _isAlreadyTakePicture.value = state
    }
}