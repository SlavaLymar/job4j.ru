select name, avg(body_id) av from car group by name having avg(body_id) > 2;