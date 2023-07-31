CREATE TABLE IF NOT EXISTS "user_list_conf"
(

    id       varchar
        constraint user_list_conf_pk primary key default uuid_generate_v4(),
    username varchar not null unique,
    password text    not null
);