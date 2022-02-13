package com.example.demo.app_service.util

data class ErrorDto(
    val handler: String,
    val message: String,
) {
    companion object {

        fun dataException(handler: String, msg: String): ErrorDto {
            return ErrorDto(handler, msg)
        }

        fun dataDuplicated(handler: String): ErrorDto {
            return ErrorDto(handler, "duplicated")
        }

        fun dataNotEqualPassword(handler: String): ErrorDto {
            return ErrorDto(handler, "not equal password")
        }

        fun dataFailedInsert(handler: String): ErrorDto {
            return ErrorDto(handler, "failed insert")
        }

        fun dataNotFound(handler: String): ErrorDto {
            return ErrorDto(handler, "not found data")
        }
    }
}
