package com.example.demo.domain.userprofile

data class UserProfileParam(
    val tel: String,
) {
    init {
        org.valiktor.validate(this) {
        }
    }
}
