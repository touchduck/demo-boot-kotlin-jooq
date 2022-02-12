package com.example.demo.jooq_handler;

import com.example.demo.jooq_domain.memo.MemoParam;
import com.example.demo.jooq_domain.memo.MemoRepo;
import com.example.demo.security.JwtTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.springframework.web.reactive.function.server.ServerResponse.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class MemoCtl {

    private final JwtTokenService jwtTokenService;
    private final MemoRepo memoRepo;

    @NotNull
    public Mono<ServerResponse> post(ServerRequest request) {

        UUID userId = jwtTokenService.getUserId(request);

        try {
            return request.bodyToMono(MemoParam.class)
                    .flatMap(memoParam -> memoRepo.create(userId, memoParam)
                            .flatMap(memoEntity -> ok().bodyValue(memoEntity))
                            .switchIfEmpty(notFound().build()));

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return badRequest().build();
        }
    }

    @NotNull
    public Mono<ServerResponse> getAll(ServerRequest request) {

        UUID userId = jwtTokenService.getUserId(request);

        try {
            return memoRepo.findAll(userId)
                    .flatMap(memoEntities -> ok().bodyValue(memoEntities))
                    .switchIfEmpty(notFound().build());

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return badRequest().build();
        }

    }

    @NotNull
    public Mono<ServerResponse> get(ServerRequest request) {

        UUID userId = jwtTokenService.getUserId(request);
        UUID memoId = UUID.fromString(request.pathVariable("id"));

        try {
            return memoRepo.findById(userId, memoId)
                    .flatMap(memoEntity -> ok().bodyValue(memoEntity))
                    .switchIfEmpty(notFound().build());

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return badRequest().build();
        }

    }

    @NotNull
    public Mono<ServerResponse> put(ServerRequest request) {

        UUID userId = jwtTokenService.getUserId(request);
        UUID memoId = UUID.fromString(request.pathVariable("id"));

        try {
            return request.bodyToMono(MemoParam.class)
                    .flatMap(memoParam -> memoRepo.update(userId, memoId, memoParam)
                            .flatMap(memoEntity -> ok().bodyValue(memoEntity))
                            .switchIfEmpty(notFound().build()));

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return badRequest().build();
        }
    }

    @NotNull
    public Mono<ServerResponse> delete(ServerRequest request) {

        UUID userId = jwtTokenService.getUserId(request);
        UUID memoId = UUID.fromString(request.pathVariable("id"));

        try {
            return memoRepo.delete(userId, memoId)
                    .flatMap(memoEntity -> ok().bodyValue(memoEntity))
                    .switchIfEmpty(notFound().build());

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return badRequest().build();
        }

    }
}
