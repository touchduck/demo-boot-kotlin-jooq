package com.example.demo.domain.util

import com.example.demo.util.Pagination

data class PaginationDto<T, E>(private val pagination: Pagination<T>, val items: List<E>) {

    var page = 0
    var size = 0L
    var total = 0L

    init {
        page = pagination.page
        size = pagination.size
        total = pagination.total
    }
}
