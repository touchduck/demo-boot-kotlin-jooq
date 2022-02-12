package com.example.demo.jooq_domain.memo;

import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

public interface MemoRepo {

    Mono<MemoEntity> create(UUID userId, MemoParam memoParam);

    Mono<List<MemoEntity>> findAll(UUID userId);

    Mono<MemoEntity> findById(UUID userId, UUID memoId);

    Mono<MemoEntity> update(UUID userId, UUID memoId, MemoParam memoParam);

    Mono<MemoEntity> delete(UUID userId, UUID memoId);
}
