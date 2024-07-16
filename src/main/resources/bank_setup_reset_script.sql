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
    name text,
    type text,
    amount REAL CHECK (amount >= 0 ) DEFAULT 0 ,
    user_id integer,
    UNIQUE(name),
    FOREIGN KEY (user_id) REFERENCES user(id)
);

insert into "user" (username, password )values ('admin', '1234');