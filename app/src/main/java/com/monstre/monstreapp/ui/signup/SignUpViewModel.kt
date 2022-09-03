package com.monstre.monstreapp.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.monstre.monstreapp.data.AuthRepository
import com.monstre.monstreapp.domain.model.User

class SignUpViewModel(private val authRepository: AuthRepository) : ViewModel() {
    fun login(email: String, password: String) = authRepository.login(email, password)

    fun signup(name: String ,email: String, password: String) = authRepository.signup(name,email,password)

    val user: LiveData<User> = authRepository.user.asLiveData()
}