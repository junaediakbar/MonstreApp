package com.monstre.monstreapp.domain.model

data class User(
    val id: String,
    val name: String,
    val token: String,
    val selectedMbti: String,
    val avatar : String
)