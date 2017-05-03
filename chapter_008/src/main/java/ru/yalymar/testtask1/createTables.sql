create table company (
	id serial,
    name character varying,
    constraint company_pkey primary key (id)
);

create table person (
	id serial,
    name character varying,
    company_id integer,
    constraint person_pkey primary key (id)
);