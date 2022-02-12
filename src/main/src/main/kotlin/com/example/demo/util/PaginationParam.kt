package com.example.demo.util

import org.springframework.web.reactive.function.server.ServerRequest

class PaginationParam(serverRequest: ServerRequest) {

    var page = 0L
    var size = 20
    var offset = 0L

    init {

        serverRequest.queryParam("page").ifPresent {
            it.toLongOrNull()?.let { value ->
                this.page = value
            }
        }

        serverRequest.queryParam("size").ifPresent {
            it.toIntOrNull()?.let { value ->
                this.size = value
            }
        }

        offset = page * size

    }

}
