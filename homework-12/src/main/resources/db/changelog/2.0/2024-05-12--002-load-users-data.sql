--liquibase formatted sql

--changeset mbortsova:2024-05-12--002-load-users-data

insert into users(username, password, role)
values ('user', 'password', 'USER'), ('admin', 'admin', 'ADMIN');

