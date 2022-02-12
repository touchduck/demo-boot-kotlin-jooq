package com.example.demo.util

data class PagingDTO<E>(
    var page: Long,
    var size: Int,
    var total: Long,
    val items: List<E>
)
