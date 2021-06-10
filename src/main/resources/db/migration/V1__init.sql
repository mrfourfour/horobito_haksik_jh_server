drop table if exists account;
drop sequence if exists hibernate_sequence;
create sequence hibernate_sequence start with 1 increment by 1;
create table account (id bigint auto_increment primary key, name varchar(255) null, password varchar(255) null);

