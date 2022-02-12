package com.example.demo.dto

data class TokenDTO(
    val access_token: String,
    val refresh_token: String,
    val token_type: String = "Bearer"
)
