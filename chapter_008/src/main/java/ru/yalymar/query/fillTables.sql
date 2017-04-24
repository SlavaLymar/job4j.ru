insert into Filter (name, user_id)
	values ('filter1', 2);
    
insert into Operators (operator)
	values ('=');
insert into Operators (operator)
	values ('>');
insert into Operators (operator)
	values ('<');
insert into Operators (operator)
	values ('>=');
insert into Operators (operator)
	values ('<=');
insert into Operators (operator)
	values ('!=');
   
insert into fields (field, filter_id, value, operator_id)
 	values (
    	'status_id', 3, '1', 1
    );
   
insert into fields (field, filter_id, value, operator_id)
 	values (
    	'id', 3, '1', 1
    );
   



    