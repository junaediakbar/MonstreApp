package com.monstre.monstreapp.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.monstre.monstreapp.data.MonstreRepository
import com.monstre.monstreapp.domain.model.User

class HistoryViewModel(private val monstreeRepository: MonstreRepository): ViewModel() {
    fun getFullYearHistory(token : String) = monstreeRepository.getFullYearHistory("Bearer $token")
    val user: LiveData<User> = monstreeRepository.user.asLiveData()
}