create table if not exists "email"
(
    id           varchar
        constraint email_pk primary key default uuid_generate_v4(),
    professional varchar unique,
    personal     varchar unique
);