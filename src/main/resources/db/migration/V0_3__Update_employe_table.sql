do
$$
    begin
        if
            not exists(select from pg_type where typname = 'sex') then
            create type sex as enum ('M', 'F');
        end if;
        if
            not exists(select from pg_type where typname = 'csp') then
            create type csp as enum ('AGRICULTURAL_WORKERS', 'CRAFTSMEN_AND_ARTISANS', 'TRADERS_AND_MERCHANTS', 'CIVIL_SERVANTS_AND_PROFESSIONALS', 'UNSKILLED_LABORERS');
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
    add column phone             varchar,
    add column address           varchar,
    add column professional_email varchar,
    add column personal_email     varchar,
    add column cin               varchar,
    add column cnaps             varchar,
    add column children_number    integer,
    add column entrance_date     date,
    add column departure_date    date,
    add column sex               sex,
    add column csp               csp;