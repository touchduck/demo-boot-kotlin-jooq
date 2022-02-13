-- auto-generated definition
create table users
(
    id                     uuid                  not null
        constraint users_pkey
            primary key,
    username               varchar(256)          not null
        constraint users_username_unique
            unique,
    password_hash          varchar(512)          not null,
    nickname               varchar(128)          not null,
    first_name             varchar(128),
    last_name              varchar(128),
    authorities            varchar(512)          not null,
    is_enabled             boolean default true  not null,
    email                  varchar(256),
    email_confirmed        boolean default false not null,
    phone_number           varchar(128),
    phone_number_confirmed boolean default false not null,
    two_factor_enabled     boolean default false not null,
    lockout_end            timestamp,
    lockout_enabled        boolean default false not null,
    access_failed_count    integer default 0     not null,
    created_at             timestamp             not null,
    updated_at             timestamp             not null,
    deleted_at             timestamp
);

alter table "user"
    owner to rockman;

create table user_profiles
(
    id         uuid         not null
        constraint user_profiles_pkey
            primary key,
    user_id    uuid         not null
        constraint fk_user_profiles_user_id_id
            references "user"
            on update restrict on delete restrict,
    first_name varchar(512) not null,
    last_name  varchar(512) not null,
    birthday   date         not null,
    tel        varchar(512) not null,
    sex        smallint     not null,
    created_at timestamp    not null,
    updated_at timestamp    not null,
    deleted_at timestamp
);

alter table user_profile
    owner to rockman;

create table memos
(
    id         uuid         not null
        constraint memos_pkey
            primary key,
    user_id    uuid         not null
        constraint fk_memos_user_id_id
            references "user"
            on update restrict on delete restrict,
    title      varchar(512) not null,
    body       text         not null,
    created_at timestamp    not null,
    updated_at timestamp    not null,
    deleted_at timestamp
);

alter table memo
    owner to rockman;
