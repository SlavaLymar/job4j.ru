insert into status (description)
	values('create');
insert into status (description)
 	values('in progress');
insert into status (description)
 	values('stop');
insert into status (description)
	values('complete');   
   
insert into category (description)
 	values ('bug');
insert into category (description)
 	values ('new feature');
insert into category (description)
 	values ('optimize');
insert into category (description)
 	values ('new soft');

insert into task (description, date_of_create, category_id, status_id) 
 	values ('create database', '4/21/2017', 4, 1);
insert into comment (description, task_id)
 	values ('create new software in 3 month', 1);

insert into attached (url, task_id)
 	values ('www.yandex.ru',1);

insert into roletable (description) values ('junior developer');
insert into roletable (description) values ('middle developer');
insert into roletable (description) values ('senior developer');
insert into roletable (description) values ('team lead');

insert into right_role (open_item, update_item, close_item, role_id) 
 	values (false, false, false, 1);
insert into right_role (open_item, update_item, close_item, role_id) 
 	values (true, false, false, 2);
insert into right_role (open_item, update_item, close_item, role_id) 
 	values (true, true, false, 3);
insert into right_role (open_item, update_item, close_item, role_id) 
 	values (true, true, true, 4);

insert into usertable (name, email, task_id, role_id)
	values ('Vasya', 'vasya@inbox.ru', 1, 1);
insert into usertable (name, email, task_id, role_id)
 	values ('Petya', 'petya@inbox.ru', 1, 2);
insert into usertable (name, email, task_id, role_id)
 	values ('Gadya', 'gadya@inbox.ru', 1, 3);
insert into usertable (name, email, task_id, role_id)
 	values ('Slava', 'slava@inbox.ru', 1, 4);









