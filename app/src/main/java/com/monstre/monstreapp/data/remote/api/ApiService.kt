package com.monstre.monstreapp.data.remote.api

import com.monstre.monstreapp.data.remote.response.*
import okhttp3.MultipartBody
import retrofit2.http.*

interface ApiService {
    @Headers(
        "Content-Type: application/x-www-form-urlencoded"
    )
    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse

    @FormUrlEncoded
    @POST("register")
    suspend fun signup(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): RegisterResponse

    @FormUrlEncoded
    @PUT("update-personality")
    suspend fun updateMbti(
        @Header("Authorization") token: String,
        @Field("personality") mbti: String,
    ): UserResponse

    @GET("profile")
    suspend fun getProfile(
        @Header("Authorization") token: String,
    ): UserResponse

    @GET("saturation")
    suspend fun getSaturation(
        @Header("Authorization") token: String,
    ): TodaySaturationResponse

    @GET("saturation/get-week")
    suspend fun getSaturationFullWeek(
        @Header("Authorization") token: String,
    ): GeneralSaturationListResponse

    @GET("saturation/get-full-month")
    suspend fun getSaturationFullMonth(
        @Header("Authorization") token: String,
    ): GeneralSaturationListResponse

    @GET("saturation/get-full-year")
    suspend fun getSaturationFullYear(
        @Header("Authorization") token: String,
    ): GeneralSaturationListResponse

    @GET("articles")
    suspend fun getArticles(
        @Header("Authorization") token: String,
    ): ArticleResponse

    @Multipart
    @POST("update-avatar")
    suspend fun updateImage(
        @Header("Authorization") token: String,
        @Part avatar: MultipartBody.Part,
    ): UserResponse
}