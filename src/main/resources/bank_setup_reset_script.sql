drop table if exists "user";
drop table if exists "account";

create table "user"(
    id integer PRIMARY KEY,
	username text,
	password text,
	UNIQUE(username)
);

create table "account" (
    id integer PRIMARY KEY ,
    type text,
    amount REAL,
    user_id integer,
    FOREIGN KEY (user_id) REFERENCES user(id)
);

insert into "user" (username, password )values ('admin', '1234');