package com.example.demo.jooq_domain.memo;

import com.example.demo.infra.hawaii.tables.records.MemosRecord;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemoEntity {

    private UUID id;
    private UUID userId;
    private String title;
    private String body;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    static MemoEntity mapper(MemosRecord data) {
        return MemoEntity.builder().id(data.getId())
                .userId(data.getUserId())
                .title(data.getTitle())
                .body(data.getBody())
                .createdAt(data.getCreatedAt())
                .updatedAt(data.getUpdatedAt())
                .deletedAt(data.getDeletedAt())
                .build();

    }

    static MemoEntity mapper(MemoParam data) {
        return MemoEntity.builder()
                .title(data.getTitle())
                .body(data.getBody())
                .build();

    }
}
