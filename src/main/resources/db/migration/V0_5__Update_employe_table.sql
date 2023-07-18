do
$$
    begin
        if
            not exists(select from pg_type where typname = 'sex') then
            create type sex as enum ('M', 'F');
        end if;
    end
$$;

CREATE TABLE IF NOT EXISTS "have_position"
(

    id          varchar
        constraint have_position_pk primary key default uuid_generate_v4(),
    employee_id varchar,
    position_id varchar,
    constraint have_position_employee_fk foreign key (employee_id)
        references "employee" (id),
    constraint have_position_position_fk foreign key (position_id)
        references "position" (id)
);

ALTER TABLE "employee"
-- id foreign key
    add column email_id               varchar,
    add column socioeconomic_group_id varchar,
-- id foreign key
    add column phone                  varchar,
    add column address                varchar,
    add column cin                    varchar,
    add column socioeconomic_group    varchar,
    add column cnaps                  varchar,
    add column entrance_date          varchar,
    add column departure_date         varchar,
    add column childrenNumber         integer,
    add column sex                    sex,
-- constraint
    add constraint employee_email_fk foreign key (email_id)
        references "email" (id),
    add constraint employee_socioeconomic_group_fk foreign key (socioeconomic_group_id)
        references "socioeconomic_group" (id);