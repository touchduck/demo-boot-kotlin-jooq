package com.example.demo.domain.auth

data class UserSettingParam(
    val firstname: String?,
    val lastname: String?,
    val tel: String?,
) {
    fun validateObj(): UserSettingParam {
        org.valiktor.validate(this) {
        }
        return this
    }
}
