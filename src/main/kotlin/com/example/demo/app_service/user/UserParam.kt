package com.example.demo.app_service.user

import org.valiktor.functions.hasSize
import org.valiktor.functions.isEmail
import org.valiktor.functions.isNotEmpty

data class UserParam(
    val username: String,
    val password: String,
    val nickname: String,
) {
    fun validateObj(): UserParam {
        org.valiktor.validate(this) {
            validate(UserParam::username).isEmail().isNotEmpty().hasSize(max = 128)
            validate(UserParam::password).isNotEmpty().hasSize(min = 6, max = 128)
            validate(UserParam::nickname).isNotEmpty().hasSize(min = 2, max = 128)
        }
        return this
    }
}
