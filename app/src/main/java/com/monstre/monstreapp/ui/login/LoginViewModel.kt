package com.monstre.monstreapp.ui.login

import androidx.lifecycle.*
import com.monstre.monstreapp.data.AuthRepository
import com.monstre.monstreapp.data.remote.response.LoginResponse
import com.monstre.monstreapp.domain.model.User
import com.monstre.monstreapp.utils.Event
import kotlinx.coroutines.launch

class LoginViewModel(private val authRepository: AuthRepository) : ViewModel() {
    fun login(email: String, password: String) = authRepository.login(email, password)

    val user: LiveData<User> = authRepository.user.asLiveData()
}