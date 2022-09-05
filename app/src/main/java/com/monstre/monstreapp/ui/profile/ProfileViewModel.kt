package com.monstre.monstreapp.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.monstre.monstreapp.data.AuthRepository
import com.monstre.monstreapp.data.MonstreRepository
import com.monstre.monstreapp.domain.model.User

class ProfileViewModel(private val monstreeRepository: MonstreRepository, private val authRepository: AuthRepository):ViewModel() {
    fun getProfile(token: String) = monstreeRepository.getProfile("Bearer $token")
    suspend fun logout(token : String) = authRepository.logout("Bearer $token")
    val user: LiveData<User> = monstreeRepository.user.asLiveData()

}