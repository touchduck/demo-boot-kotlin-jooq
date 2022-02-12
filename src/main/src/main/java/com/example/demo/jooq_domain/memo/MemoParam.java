package com.example.demo.jooq_domain.memo;

import lombok.*;
import org.modelmapper.ModelMapper;

@Builder
@NoArgsConstructor(staticName = "create", access = AccessLevel.PUBLIC)
@AllArgsConstructor(staticName = "create", access = AccessLevel.PUBLIC)
@Data
public class MemoParam {

    private String title;
    private String body;

    public static MemoParam mapper(Object data) {
        return new ModelMapper().map(data, MemoParam.class);
    }

}
