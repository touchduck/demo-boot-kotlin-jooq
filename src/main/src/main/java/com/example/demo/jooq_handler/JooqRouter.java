package com.example.demo.jooq_handler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class JooqRouter {

    private final String apiV1 = "/api/v1";

    @Bean
    public RouterFunction<ServerResponse> routeJooqMemo(MemoCtl handler) {

        return route()
                .path(apiV1 + "/memos_ex", builder -> {
                    builder
                            .POST("", accept(APPLICATION_JSON), handler::post)
                            .GET("", accept(APPLICATION_JSON), handler::getAll)
                            .GET("/{id}", accept(APPLICATION_JSON), handler::get)
                            .PUT("/{id}", accept(APPLICATION_JSON), handler::put)
                            .DELETE("/{id}", accept(APPLICATION_JSON), handler::delete)
                            .build();
                }).build();

    }
}
