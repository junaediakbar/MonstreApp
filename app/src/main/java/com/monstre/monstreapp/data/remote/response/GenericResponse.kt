package com.monstre.monstreapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class GenericResponse(

    @field:SerializedName("message")
    val message: String
)
