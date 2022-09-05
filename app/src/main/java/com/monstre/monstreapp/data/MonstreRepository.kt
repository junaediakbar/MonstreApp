package com.monstre.monstreapp.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.monstre.monstreapp.data.local.preference.SharedPreference
import com.monstre.monstreapp.data.remote.api.ApiService
import com.monstre.monstreapp.data.remote.api.ApiServiceSmartWatch
import com.monstre.monstreapp.data.remote.response.*
import com.monstre.monstreapp.domain.model.User
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

class MonstreRepository(
    private val pref: SharedPreference,
    private val apiService: ApiService,
    private val smartWatchApiService: ApiServiceSmartWatch
) {

    fun getProfile(token: String): LiveData<Result<UserResponse>> =  liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getProfile(token)
            pref.saveAvatar(response.avatar)
            pref.saveId(response.id.toString())
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.d(MonstreRepository.TAG, "profile: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun postSaturationToday(token: String,bpm: String,spo2: String) : LiveData<Result<PostTodaySaturationResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.postTodaySaturation(token,bpm,spo2)
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.d(MonstreRepository.TAG, "Today saturation: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getSmartWatchSaturation(): LiveData<Result<SmartWatchResponse>> =  liveData {
        emit(Result.Loading)
        try {
            val response = smartWatchApiService.getSmartWatchData()
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.d(MonstreRepository.TAG, "saturation: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
    }


    fun getSaturation(token: String): LiveData<Result<TodaySaturationResponse>> =  liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getSaturation(token)
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.d(MonstreRepository.TAG, "saturation: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getArticles(token: String): LiveData<Result<ArticleResponse>> =  liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getArticles(token)
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.d(MonstreRepository.TAG, "articles: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getSaturationFullWeek(token: String): LiveData<Result<GeneralSaturationListResponse>> =  liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getSaturationFullWeek(token)
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.d(MonstreRepository.TAG, "saturation: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getSaturationFullMonth(token: String): LiveData<Result<GeneralSaturationListResponse>> =  liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getSaturationFullMonth(token)
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.d(MonstreRepository.TAG, "saturation: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getFullYearHistory(token: String): LiveData<Result<GeneralSaturationListResponse>> =  liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getSaturationFullYear(token)
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.d(MonstreRepository.TAG, "saturation: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun updateImage(imageMultipart: MultipartBody.Part, token:  String): LiveData<Result<UserResponse>> =  liveData {
        emit(Result.Loading)
        try {
            val response = apiService.updateImage( imageMultipart, token)
            emit(Result.Success(response))
            pref.saveAvatar(response.avatar)
            pref.saveId(response.id.toString())
        } catch (e: Exception) {
            Log.d(MonstreRepository.TAG, "updateImage: ${e.message.toString()} ")
            emit(Result.Error(e.message.toString()))
        }
    }

    val user: Flow<User>
        get() = pref.getData()

    companion object {
        private const val TAG = "MonstreRepository"

        @Volatile
        private var instance: MonstreRepository? = null
        fun getInstance(
            pref: SharedPreference,
            apiService: ApiService,
            apiServiceSmartWatch: ApiServiceSmartWatch
        ): MonstreRepository =
            instance ?: synchronized(this) {
                instance ?: MonstreRepository(pref, apiService,apiServiceSmartWatch)
            }.also { instance = it }
    }

}