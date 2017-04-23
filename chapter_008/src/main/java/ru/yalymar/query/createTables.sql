create table FilterTable (
	id serial not null primary key,
    name char(20),
    user_id integer references UserTable (id)
);

create table Operators (
	id serial not null primary key,
    operator char(6),
    filter_id integer references FilterTable(id)
);

create table ValuesTable(
	id serial not null primary key,
    value char(10),
    filter_id integer references FilterTable(id)
);