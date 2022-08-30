package com.monstre.monstreapp.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.monstre.monstreapp.data.local.preference.SharedPreference
import com.monstre.monstreapp.ui.camera.CameraViewModel
import com.monstre.monstreapp.ui.mbti.MbtiViewModel

class ViewModelFactory(private val pref: SharedPreference, private val context: Context) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MbtiViewModel::class.java) -> {
                MbtiViewModel() as T
            }
            modelClass.isAssignableFrom(CameraViewModel::class.java) -> {
                CameraViewModel() as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}