package com.example.demo.app_service.auth

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
