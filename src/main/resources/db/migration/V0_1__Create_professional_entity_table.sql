create extension if not exists "uuid-ossp";

create table if not exists "professional_entity"
(
    id                 varchar
        constraint professional_entity_pk primary key default uuid_generate_v4(),
    image              text,
    professional_email varchar not null unique,
    address            varchar not null
);