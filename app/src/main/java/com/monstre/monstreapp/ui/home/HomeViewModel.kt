package com.monstre.monstreapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData

import com.monstre.monstreapp.data.MonstreRepository
import com.monstre.monstreapp.domain.model.User

class HomeViewModel(private val monstreeRepository: MonstreRepository): ViewModel() {

    fun getSaturation(token: String) = monstreeRepository.getSaturation("Bearer $token")
    val user: LiveData<User> = monstreeRepository.user.asLiveData()

    fun getSaturationFullWeek (token: String) = monstreeRepository.getSaturationFullWeek("Bearer $token")

    fun getSaturationFullMonth (token: String) = monstreeRepository.getSaturationFullMonth("Bearer $token")

    fun getArticles(token: String) = monstreeRepository.getArticles("Bearer $token")

    private var _badges = MutableLiveData <String>()
    val badges: LiveData<String> = _badges

    fun setBadges(time : String){
        _badges.value = time
    }

    init {
        _badges.value = "Week"
    }

}