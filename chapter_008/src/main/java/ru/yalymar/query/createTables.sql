create table Filter (
	id serial not null primary key,
    name char(20),
    user_id integer references Usertable(id)
);

create table Operators(
	id serial not null primary key,
    operator char(7)
);

create table Fields (
	id serial not null primary key,
    field char(10),
    filter_id integer references Filter(id),
    value char(10),
    operator_id integer references Operators(id)
);