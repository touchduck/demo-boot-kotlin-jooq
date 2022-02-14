package com.example.demo.config

import com.example.demo.app_handler.SignUpHandler
import com.example.demo.app_handler.MemoHandler
import com.example.demo.app_handler.TokenHandler
import com.example.demo.app_handler.UserHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class RoutesConfiguration {

    @Bean
    fun authRoutes(handler: SignUpHandler) = coRouter {
        "/api/v1/signup".nest {
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
    fun userRoutes(handler: UserHandler) = coRouter {
        "/api/v1/token".nest {
            PUT("/{id}", handler::update)
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
