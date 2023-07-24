create extension if not exists "uuid-ossp";

create table if not exists "enterprise"
(
    id              varchar
        constraint enterprise_pk primary key default uuid_generate_v4(),
    name            varchar,
    description     text,
    slogan          text    not null,
    tax_identity_id varchar not null,
    constraint enterprise_tax_identity foreign key (tax_identity_id) references "tax_identity" (id)
) inherits ("professional_entity");