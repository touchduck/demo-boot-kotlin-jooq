package com.example.demo.app_service.auth

data class UserParam(
    val firstname: String?,
    val lastname: String?,
    val tel: String?,
) {
    fun validateObj(): UserParam {
        org.valiktor.validate(this) {
        }
        return this
    }
}
