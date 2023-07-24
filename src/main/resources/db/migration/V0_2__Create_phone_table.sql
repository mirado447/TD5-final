CREATE TABLE IF NOT EXISTS "phone"
(
    id                     varchar
        constraint phone_pk primary key default uuid_generate_v4(),
    professional_entity_id varchar,
    value                  varchar not null unique,
    constraint phone_professional_entity foreign key (professional_entity_id) references "professional_entity" (id)
);
