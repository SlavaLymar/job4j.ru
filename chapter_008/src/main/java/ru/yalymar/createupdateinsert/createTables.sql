-- create task`s table with column: id, descriprion, date_of_create, category_id, status_id
create table Task (
	id serial primary key,
    description text,
    date_of_create date,
    category_id integer references Category (id),
    status_id integer references Status (id)
);

-- create user`s table with column: id, name (up to 20 chars), 
-- email (up to 30 chars), Task_id? Role_id
create table UserTable (
	id serial not null primary key,
    name char(20),
    email char(30),
    Task_id integer references Task (id),
    Role_id integer references RoleTable (id)
);

-- create role`s table with column: id, description
create table RoleTable (
	id serial not null primary key,
    description text
);

-- create right`s table with column: id, open_item, update_utem, close_item, Role_id
create table Right_Role (
	id serial not null primary key,
    open_item boolean,
    update_item boolean,
    close_item boolean,
    Role_id integer,
    foreign key (Role_id) references RoleTable (id)
);

-- create status` table whith column: id, description
create table Status (
	id serial not null primary key,
    description text
);

-- create comment`s table with column: id, description, Task_id
create table Comment (
	id serial not null primary key,
    description text,
    Task_id integer,
    foreign key (Task_id) references Task (id)
);

-- create table of attached with column: id, URL (up to 100 chars), Task_id
create table Attached (
	id serial not null primary key,
    URL char(100),
    Task_id integer,
    foreign key (Task_id) references Task (id)
);

-- create category`s table with column: id, description
create table Category (
	id serial primary key,
    description text
);
