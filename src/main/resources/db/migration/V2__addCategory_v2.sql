
create table category(
                         id bigint generated always as identity not null primary key,
                         name varchar(100),
                         description varchar(1000),
                         image varchar(200)
);