package com.example.demo.util

data class PagingDto<E>(
    var page: Long,
    var size: Int,
    var total: Long,
    val items: List<E>
)
