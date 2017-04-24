select t.id, t.description, t.date_of_create, c.description, s.description
from task as t
inner join category as c 
on t.category_id = c.id
inner join status as s
on t.status_id = s.id
where t.description like '%crea%' and s.description like '%crea%';
