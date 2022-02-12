package com.example.demo.dto

data class ErrorDTO(
    val handler: String,
    val message: String,
) {
    companion object {

        fun dataException(handler: String, msg: String): ErrorDTO {
            return ErrorDTO(handler, msg)
        }

        fun dataDuplicated(handler: String): ErrorDTO {
            return ErrorDTO(handler, "duplicated")
        }

        fun dataNotEqualPassword(handler: String): ErrorDTO {
            return ErrorDTO(handler, "not equal password")
        }

        fun dataFailedInsert(handler: String): ErrorDTO {
            return ErrorDTO(handler, "failed insert")
        }

        fun dataNotFound(handler: String): ErrorDTO {
            return ErrorDTO(handler, "not found data")
        }
    }
}
