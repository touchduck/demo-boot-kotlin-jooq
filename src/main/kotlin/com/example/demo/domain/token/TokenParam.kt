package com.example.demo.domain.token

import org.valiktor.functions.hasSize
import org.valiktor.functions.isEmail
import org.valiktor.functions.isNotEmpty

data class TokenParam(
    val username: String,
    val password: String
) {
    fun validateObj(): TokenParam {
        org.valiktor.validate(this) {
            validate(TokenParam::username).isEmail().isNotEmpty().hasSize(max = 128)
            validate(TokenParam::password).isNotEmpty().hasSize(min = 6, max = 128)
        }
        return this
    }
}
