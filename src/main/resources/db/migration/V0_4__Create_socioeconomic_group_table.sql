create table if not exists "socioeconomic_group"
(
    id          varchar
        constraint socioeconomic_group_pk primary key default uuid_generate_v4(),
    name        varchar,
    description varchar
);