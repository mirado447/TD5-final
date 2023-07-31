CREATE TABLE IF NOT EXISTS "authenticated"
(
    id         varchar
        constraint authenticated_pk primary key default uuid_generate_v4(),
    session_id varchar not null unique,
    timeout    date,
    user_id    varchar,
    constraint authenticated_user foreign key (user_id) references "user_list_conf" (id)
);