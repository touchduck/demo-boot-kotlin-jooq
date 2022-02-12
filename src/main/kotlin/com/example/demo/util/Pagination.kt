package com.example.demo.util

class Pagination<T>(
    val page: Int,
    val size: Long,
    val total: Long,
    val items: List<T>
)
