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

insert into users (id, username, password, first_name, last_name, phone, role, image, reg_date, is_enabled, non_credentials_expired, non_locked, non_expired) values (1, 'user1', 'password', 'Semen', 'Matveev', '+7(910)510-85-73', 'USER', '/anyimagelink', '2009-06-04 18:13:56', true, true, true, true);
insert into users (id, username, password, first_name, last_name, phone, role, image, reg_date, is_enabled, non_credentials_expired, non_locked, non_expired) values (2, 'user2', 'password', 'Valentin', 'Popov', '+7(903)430-09-09', 'ADMIN', '/anotherimagelink', '2010-10-04 18:20:56', true, true, true, true);

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

insert into ads (id, image, price, title, user_id, created_at, description) values (1, '/adImage1', 12000, 'first ad', 1,'2009-06-04 18:13:56', 'description for first ad');
insert into ads (id, image, price, title, user_id, created_at, description) values (2, '/adImage2', 13000, 'second ad', 2,'2010-10-06 18:13:56', 'description for second ad');

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
