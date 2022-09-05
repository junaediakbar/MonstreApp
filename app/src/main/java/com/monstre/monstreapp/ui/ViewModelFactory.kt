package com.monstre.monstreapp.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.monstre.monstreapp.data.local.preference.SharedPreference
import com.monstre.monstreapp.di.Injection
import com.monstre.monstreapp.ui.camera.CameraViewModel
import com.monstre.monstreapp.ui.device.DeviceViewModel
import com.monstre.monstreapp.ui.history.HistoryViewModel
import com.monstre.monstreapp.ui.home.HomeViewModel
import com.monstre.monstreapp.ui.login.LoginViewModel
import com.monstre.monstreapp.ui.mbti.MbtiViewModel
import com.monstre.monstreapp.ui.profile.ProfileViewModel
import com.monstre.monstreapp.ui.signup.SignUpViewModel

class ViewModelFactory(private val pref: SharedPreference, private val context: Context) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MbtiViewModel::class.java) -> {
                MbtiViewModel(Injection.provideAuthRepository(context)) as T
            }
            modelClass.isAssignableFrom(CameraViewModel::class.java) -> {
                CameraViewModel(Injection.provideMonstreeRepository(context)) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(Injection.provideAuthRepository(context)) as T
            }
            modelClass.isAssignableFrom(DeviceViewModel::class.java) -> {
                DeviceViewModel(Injection.provideAuthRepository(context)) as T
            }
            modelClass.isAssignableFrom(SignUpViewModel::class.java) -> {
                SignUpViewModel(Injection.provideAuthRepository(context)) as T
            }
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> {
                AuthViewModel(Injection.provideAuthRepository(context)) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(
                    Injection.provideMonstreeRepository(context),
                    Injection.provideAuthRepository(context)
                ) as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(Injection.provideMonstreeRepository(context)) as T
            }
            modelClass.isAssignableFrom(HistoryViewModel::class.java) -> {
                HistoryViewModel(Injection.provideMonstreeRepository(context)) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}