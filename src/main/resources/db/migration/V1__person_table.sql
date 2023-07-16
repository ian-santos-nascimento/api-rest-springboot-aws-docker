
CREATE TABLE IF NOT EXISTS public.person
(
    id bigint NOT NULL ,
    first_name VARCHAR(80)  NOT NULL,
    last_name VARCHAR(80) NOT NULL,
    address VARCHAR(100) ,
    gender VARCHAR(6) NOT NULL,
    CONSTRAINT person_pkey PRIMARY KEY (id)
    );
CREATE SEQUENCE person_id_seq MINVALUE 2;
ALTER TABLE person ALTER id SET DEFAULT nextval('person_id_seq');
ALTER SEQUENCE person_id_seq OWNED BY person.id

