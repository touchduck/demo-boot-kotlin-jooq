package com.example.demo.domain.memo

import org.valiktor.functions.hasSize

data class MemoParam(
    val title: String,
    val body: String
) {
    fun validateObj(): MemoParam {
        org.valiktor.validate(this) {
            validate(MemoParam::title).hasSize(max = 256)
            validate(MemoParam::body).hasSize(max = 65535)
        }
        return this
    }
}
