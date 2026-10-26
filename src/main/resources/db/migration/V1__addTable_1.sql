create table users(
                      id       bigint generated always as identity not null primary key,
                      username varchar(100),
                      password varchar(100)
);

create table roles(
                      id   bigint generated always as identity not null primary key,
                      name varchar(100)
);

create table roles_has_users(
                                id      bigint generated always as identity not null primary key,
                                user_id bigint,
                                role_id bigint
);

ALTER TABLE roles_has_users
    ADD CONSTRAINT fk_user_id
        FOREIGN KEY (user_id) REFERENCES
            users (id);

ALTER TABLE roles_has_users
    ADD CONSTRAINT fk_role_id
        FOREIGN KEY (role_id) REFERENCES
            roles(id);