CREATE TABLE IF NOT EXISTS "phone"
(
    id                     varchar
        constraint phone_pk primary key default uuid_generate_v4(),
    professional_entity_id varchar references "professional_entity" (id),
    value                  varchar not null unique
);
