package com.example.demo.util

import com.example.demo.domain.user.UserParam
import com.example.demo.dto.SignInDTO
import com.example.demo.dto.TokenDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.returnResult

open class AuthUtil(@Autowired open val client: WebTestClient) {

    var token: String = ""
        get() = "Bearer $field"

    val apiV1 = "/api/v1"

    fun signUp() {

        for (cnt in 1..3) {

            val param = UserParam(
                username = "kuma$cnt@gmail.com",
                password = "starbucks",
                nickname = "くまさん $cnt",
            )

            val result = client.post().uri("/api/v1/auth/signup")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(param)
                .exchange()

            result.expectStatus().isCreated
        }

    }

    fun signIn() {

        val param = SignInDTO(
            username = "kuma1@gmail.com",
            password = "starbucks"
        )

        val result = client.post().uri("/api/v1/token")
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(param)
            .exchange()

        result.expectStatus().isOk

        val dto = result.returnResult<TokenDTO>().responseBody.blockFirst()
        token = dto!!.access_token
    }

    fun apiPostExchange(uri: String, param: Any): WebTestClient.ResponseSpec {
        return client.post().uri(uri)
            .header(HttpHeaders.AUTHORIZATION, token)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON).bodyValue(param).exchange()
    }

    fun apiGetExchange(uri: String): WebTestClient.ResponseSpec {
        return client.get().uri(uri)
            .header(HttpHeaders.AUTHORIZATION, token)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON).exchange()
    }

    fun apiPutExchange(uri: String, param: Any): WebTestClient.ResponseSpec {
        return client.put().uri(uri)
            .header(HttpHeaders.AUTHORIZATION, token)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON).bodyValue(param).exchange()
    }

    fun apiDeleteExchange(uri: String): WebTestClient.ResponseSpec {
        return client.delete().uri(uri)
            .header(HttpHeaders.AUTHORIZATION, token)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON).exchange()
    }

}
