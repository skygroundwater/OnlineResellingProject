-- liquibase formatted sql

-- changeset skygroundwater:1

create table users
(
    id                      bigserial
        constraint users_pk
            primary key,
    username                varchar,
    password                varchar,
    first_name              varchar,
    last_name               varchar,
    phone                   varchar,
    role                    varchar,
    image                   varchar,
    reg_date                timestamp,
    non_expired             boolean default true,
    non_locked              boolean default true,
    non_credentials_expired boolean default true,
    is_enabled              boolean default true
);

create index users_username_index
    on users (username);

create table ads
(
    id          bigserial
        constraint ad_pk
            primary key,
    image       varchar,
    price       integer,
    title       varchar(1500),
    user_id     bigint
        constraint ads_users_id_fk
            references users,
    created_at  timestamp,
    description varchar(1500)
);

create table comments
(
    id         bigserial
        constraint comments_pk
            primary key,
    user_id    bigint
        constraint comments_users_id_fk
            references users,
    ad_id      bigint
        constraint comments_ads_id_fk
            references ads,
    created_at timestamp,
    text       varchar(1500)
);