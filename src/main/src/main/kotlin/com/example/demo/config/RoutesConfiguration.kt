package com.example.demo.config

import com.example.demo.handler.AuthHandler
import com.example.demo.handler.MemoHandler
import com.example.demo.handler.UserProfileHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class RoutesConfiguration {

    private val apiV1 = "/api/v1"

    @Bean
    fun authRoutes(handler: AuthHandler) = coRouter {
        "$apiV1/auth".nest {
            POST("/signup", handler::signUp)
        }
    }

    @Bean
    fun tokenRoutes(handler: AuthHandler) = coRouter {
        "$apiV1/token".nest {
            POST("", handler::createToken)
        }
    }

    @Bean
    fun userProfilesRoutes(handler: UserProfileHandler) = coRouter {
        "$apiV1/userprofile".nest {
            POST("", handler::create)
            GET("", handler::findById)
            PUT("", handler::update)
            DELETE("", handler::delete)
        }
    }

    @Bean
    fun memosRoutes(handler: MemoHandler) = coRouter {
        "$apiV1/memos".nest {
            POST("", handler::create)
            GET("", handler::findAll)
            GET("/{id}", handler::findById)
            PUT("/{id}", handler::update)
            DELETE("/{id}", handler::delete)
        }
    }

}
