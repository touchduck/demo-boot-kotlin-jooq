package com.example.demo.util

class Pagination<T>(
    val page: Long,
    val size: Int,
    val total: Long,
    val items: List<T>
)
