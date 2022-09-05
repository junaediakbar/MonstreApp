package com.monstre.monstreapp.data.remote.api

import com.monstre.monstreapp.data.remote.response.SmartWatchResponse
import com.monstre.monstreapp.data.remote.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Header


interface ApiServiceSmartWatch {
    @GET("sensor/last")
    suspend fun getSmartWatchData(
    ): SmartWatchResponse

}