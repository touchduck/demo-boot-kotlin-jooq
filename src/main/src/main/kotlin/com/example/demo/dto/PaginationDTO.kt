package com.example.demo.dto

import com.example.demo.util.Pagination

data class PaginationDTO<T, E>(private val pagination: Pagination<T>, val items: List<E>) {

    var page = 0L
    var size = 0
    var total = 0L

    init {
        page = pagination.page
        size = pagination.size
        total = pagination.total
    }
}
