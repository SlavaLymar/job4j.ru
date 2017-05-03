select name, number 
from (
		select c.name, count(1) as number
    	from person p
    	inner join company c on p.company_id = c.id 
    	group by c.name
	  ) v_1,
      (
      	select max(number) as max_number
        from (
            	select c.name, count(1) as number
            	from person p
    			inner join company c on p.company_id = c.id 
    			group by c.name  
              ) v_0
          ) v_2
where v_1.number = v_2.max_number
