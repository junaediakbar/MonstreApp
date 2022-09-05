package com.monstre.monstreapp.ui.device

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.monstre.monstreapp.data.AuthRepository
import com.monstre.monstreapp.domain.model.User

class DeviceViewModel(private val authRepository: AuthRepository) : ViewModel() {
    fun login(email: String, password: String) = authRepository.login(email, password)
    suspend fun logout(token : String) = authRepository.logout(token)
    val user: LiveData<User> = authRepository.user.asLiveData()
}