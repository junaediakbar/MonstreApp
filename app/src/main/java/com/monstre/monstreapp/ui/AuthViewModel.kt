package com.monstre.monstreapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.monstre.monstreapp.data.AuthRepository
import com.monstre.monstreapp.domain.model.User

class AuthViewModel(authRepository: AuthRepository) : ViewModel() {
    val user: LiveData<User> = authRepository.user.asLiveData()
}
