package com.monstre.monstreapp.domain.model

data class User(
    val name: String,
    val token: String,
    val selectedMbti: String
)