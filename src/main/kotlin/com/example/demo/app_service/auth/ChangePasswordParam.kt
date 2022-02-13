package com.example.demo.app_service.auth

import org.valiktor.functions.hasSize
import org.valiktor.functions.isNotEmpty

data class ChangePasswordParam(
    val password: String,
) {
    fun validateObj(): ChangePasswordParam {
        org.valiktor.validate(this) {
            validate(ChangePasswordParam::password).isNotEmpty().hasSize(min = 6, max = 128)
        }
        return this
    }
}
