package com.example.demo.jooq_domain.memo;

import com.example.demo.infra.hawaii.tables.Memos;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Record1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Transactional
@Repository
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class MemoRepoImpl implements MemoRepo {

    private static final Logger log = LoggerFactory.getLogger(MemoRepoImpl.class);

    private final DSLContext dsl;

    @Override
    public Mono<MemoEntity> create(UUID userId, MemoParam memoParam) {

        Record1<UUID> memoId =
                dsl.insertInto(Memo.MEMO, Memo.MEMO.ID, Memo.MEMO.USER_ID, Memo.MEMO.TITLE, Memo.MEMO.BODY, Memo.MEMO.CREATED_AT, Memo.MEMO.UPDATED_AT)
                        .values(UUID.randomUUID(), userId, memoParam.getTitle(), memoParam.getBody(), LocalDateTime.now(), LocalDateTime.now())
                        .returningResult(Memo.MEMO.ID)
                        .fetchOne();

        return findById(userId, memoId.value1());
    }

    @Override
    public Mono<List<MemoEntity>> findAll(UUID userId) {

        List<MemoEntity> memoEntities = dsl.select().from(Memo.MEMO)
                .where(Memo.MEMO.USER_ID.eq(userId))
                .fetchInto(MemoEntity.class);

        return Mono.just(memoEntities);
    }

    @Override
    public Mono<MemoEntity> findById(UUID userId, UUID memoId) {

        MemoEntity memoEntity = dsl.select().from(Memo.MEMO)
                .where(Memo.MEMO.ID.eq(memoId).and(Memo.MEMO.USER_ID.eq(userId)))
                .fetchOneInto(MemoEntity.class);

        return Mono.justOrEmpty(memoEntity);
    }

    @Override
    public Mono<MemoEntity> update(UUID userId, UUID memoId, MemoParam memoParam) {

        int ret = dsl.update(Memo.MEMO)
                .set(Memo.MEMO.TITLE, memoParam.getTitle())
                .set(Memo.MEMO.BODY, memoParam.getBody())
                .where(Memo.MEMO.ID.eq(memoId).and(Memo.MEMO.USER_ID.eq(userId)))
                .execute();

        if (ret > 0) {
            return findById(userId, memoId);
        }

        return Mono.empty();
    }

    @Override
    public Mono<MemoEntity> delete(UUID userId, UUID memoId) {

        int ret = dsl.delete(Memo.MEMO)
                .where(Memo.MEMO.ID.eq(memoId).and(Memo.MEMO.USER_ID.eq(userId)))
                .execute();

        if (ret > 0) {
            return findById(userId, memoId);
        }

        return Mono.empty();
    }

}
