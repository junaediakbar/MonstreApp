package com.monstre.monstreapp.ui.mbti

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.monstre.monstreapp.data.AuthRepository
import com.monstre.monstreapp.domain.model.Mbti
import com.monstre.monstreapp.domain.model.User
import com.monstre.monstreapp.utils.mbtiList

class MbtiViewModel (private val authRepository: AuthRepository): ViewModel() {
    fun updateMbti( token : String, mbti : String) = authRepository.updateMbti("Bearer $token", mbti )

    val user: LiveData<User> = authRepository.user.asLiveData()

    private var _selectedMbti = MutableLiveData <String>()
    val selectedMbti: LiveData<String> = _selectedMbti


    fun updateStateMbti(mbti :String){
        _selectedMbti.value = mbti
    }

    init {
        _selectedMbti.value =""
    }
}