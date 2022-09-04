package com.monstre.monstreapp.data

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.monstre.monstreapp.data.remote.api.ApiService
import com.monstre.monstreapp.data.remote.response.LoginResponse
import com.monstre.monstreapp.data.Result
import com.monstre.monstreapp.data.local.preference.SharedPreference
import com.monstre.monstreapp.data.remote.response.RegisterResponse
import com.monstre.monstreapp.domain.model.User
import kotlinx.coroutines.flow.Flow

class AuthRepository(
    private val pref: SharedPreference,
    private val apiService: ApiService
) {
    fun login(email: String, password: String): LiveData<Result<LoginResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.login(email, password)

            val message = response.message
            val words = message.split("[,.!?\\s]+".toRegex())
            val name = words[1]
            pref.saveUser(
                name,
                response.accessToken,
            )

            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.d(TAG, "login: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun signup(name: String, email: String, password: String): LiveData<Result<RegisterResponse>> =
        liveData {
            emit(Result.Loading)
            try {
                val response = apiService.signup(name, email, password)
                emit(Result.Success(response))
                pref.saveUser(
                    response.data.name,
                    response.accessToken,
                )
            } catch (e: Exception) {
                Log.d(TAG, "signup: ${e.message.toString()} ")
                emit(Result.Error(e.message.toString()))
            }
        }

    suspend fun logout() {
        pref.saveUser(
            "",
            ""
        )
        pref.saveAvatar(
            ""
        )
        pref.saveSelectedMbti(
            ""
        )
        pref.saveId("")
    }

    fun updateMbti(token: String, mbti: String ) = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.updateMbti(token,mbti)
            emit(Result.Success(response))
            pref.saveSelectedMbti(mbti)
        } catch (e: Exception) {
            Log.d(TAG, "signup: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
    }


    val user: Flow<User>
        get() = pref.getData()

    companion object {
        private const val TAG = "AuthRepository"

        @Volatile
        private var instance: AuthRepository? = null
        fun getInstance(
            pref: SharedPreference,
            apiService: ApiService
        ): AuthRepository =
            instance ?: synchronized(this) {
                instance ?: AuthRepository(pref, apiService)
            }.also { instance = it }
    }

}