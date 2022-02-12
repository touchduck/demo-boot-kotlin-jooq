package com.example.demo.config

import com.example.demo.app_service.auth.AuthHandler
import com.example.demo.app_service.memo.MemoHandler
import com.example.demo.app_service.token.TokenHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class RoutesConfiguration {

    @Bean
    fun authRoutes(handler: AuthHandler) = coRouter {
        "/api/v1/auth".nest {
            POST("", handler::create)
        }
    }

    @Bean
    fun tokenRoutes(handler: TokenHandler) = coRouter {
        "/api/v1/token".nest {
            POST("", handler::create)
        }
    }

    @Bean
    fun memosRoutes(handler: MemoHandler) = coRouter {
        "/api/v1/memos".nest {
            POST("", handler::create)
            GET("", handler::getAll)
            GET("/{id}", handler::getById)
            PUT("/{id}", handler::update)
            DELETE("/{id}", handler::delete)
        }
    }

}
