select b.type from car as c right join body as b on c.body_id = b.id where c.name is null;
select e.type from car as c right join engine as e on c.engine_id = e.id where c.name is null;
select t.type from car as c right join transmission as t on c.transmission_id = t.id where c.name is null;