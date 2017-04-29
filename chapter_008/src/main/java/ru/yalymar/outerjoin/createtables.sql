create table transmission(
	id serial primary key,
    type char(20)
);

create table engine(
	id serial primary key,
    type char(20)
);

create table body(
	id serial primary key,
    type char(20)
);

create table car(
	id serial primary key,
    name char(50),
    body_id int references body (id),
    engine_id int references engine (id),
    transmission_id int references transmission (id)
);