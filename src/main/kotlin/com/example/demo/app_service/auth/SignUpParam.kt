package com.example.demo.app_service.auth

import org.valiktor.functions.hasSize
import org.valiktor.functions.isEmail
import org.valiktor.functions.isNotEmpty

data class SignUpParam(
    val username: String,
    val password: String,
    val nickname: String,
) {
    fun validateObj(): SignUpParam {
        org.valiktor.validate(this) {
            validate(SignUpParam::username).isEmail().isNotEmpty().hasSize(max = 128)
            validate(SignUpParam::password).isNotEmpty().hasSize(min = 6, max = 128)
            validate(SignUpParam::nickname).isNotEmpty().hasSize(min = 2, max = 128)
        }
        return this
    }
}
