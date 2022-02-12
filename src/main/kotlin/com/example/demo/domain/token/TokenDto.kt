package com.example.demo.domain.token

data class TokenDto(
    val access_token: String,
    val refresh_token: String,
    val token_type: String = "Bearer"
)
