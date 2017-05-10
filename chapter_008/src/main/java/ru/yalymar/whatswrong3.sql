SELECT id, name
   FROM car
   WHERE body_id =(SELECT MAX(body_id)
                  FROM car);