-- insert into body
insert into body (type) values ('hatchback');
insert into body (type) values ('sedan');
insert into body (type) values ('suv');
insert into body (type) values ('wagon');
insert into body (type) values ('pickup');

-- insert into engine
insert into engine (type) values ('petrol');
insert into engine (type) values ('diesel');
insert into engine (type) values ('hybrid');
insert into engine (type) values ('natural gas');

-- insert into transmission
insert into transmission (type) values ('manual');
insert into transmission (type) values ('auto');

-- insert into car
insert into car(name, body_id, engine_id, transmission_id) values
	('ford focus', 4, 1, 2);
insert into car(name, body_id, engine_id, transmission_id) values
	('ford focus', 1, 1, 1);
insert into car(name, body_id, engine_id, transmission_id) values
	('ford focus', 2, 1, 2);
insert into car(name, body_id, engine_id, transmission_id) values
	('kia soul', 3, 2, 2);
insert into car(name, body_id, engine_id, transmission_id) values
	('kia soul', 3, 1, 1);