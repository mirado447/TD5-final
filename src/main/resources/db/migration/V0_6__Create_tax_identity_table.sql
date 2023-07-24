create table if not exists "tax_identity"
(
    id    varchar
        constraint tax_identity_pk primary key default uuid_generate_v4(),
    nif varchar not null check (nif ~ '^[A-Za-z0-9 ]+$'),
    stat  varchar not null check (stat ~ '^[A-Za-z0-9 ]+$'),
    rcs   varchar not null check (rcs ~ '^[A-Za-z0-9 ]+$')
);