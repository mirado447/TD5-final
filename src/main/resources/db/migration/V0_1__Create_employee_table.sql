create extension if not exists "uuid-ossp";

create table if not exists "employee"
(
    id                  varchar
        constraint employee_pk primary key default uuid_generate_v4(),
    first_name           varchar,
    last_name            varchar,
    image               oid,
    registration_number varchar,
    birth_date          date
);

CREATE SEQUENCE if not exists employ_ref_sequence
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 999999
    CACHE 1;

CREATE OR REPLACE FUNCTION generate_employ_custom_ref()
    RETURNS TRIGGER AS
$BODY$
BEGIN
    NEW.registration_number := 'REF-' || LPAD(NEXTVAL('employ_ref_sequence')::TEXT, 6, '0');
    RETURN NEW;
END;
$BODY$
    LANGUAGE plpgsql;

CREATE TRIGGER insert_reference_number_trigger
    BEFORE INSERT
    ON "employee"
    FOR EACH ROW
EXECUTE FUNCTION generate_employ_custom_ref();