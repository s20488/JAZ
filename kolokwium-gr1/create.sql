create table employment (id integer generated by default as identity (start with 1), key_skills varchar(255), title varchar(255), person_id integer, primary key (id));
create table user (id integer generated by default as identity (start with 1), first_name varchar(255), last_name varchar(255), primary key (id));
alter table employment add constraint FKg403g4q35xp6hpnqstog8q45h foreign key (person_id) references user;
create table employment (id integer generated by default as identity (start with 1), key_skills varchar(255), title varchar(255), person_id integer, primary key (id));
create table user (id integer generated by default as identity (start with 1), first_name varchar(255), last_name varchar(255), primary key (id));
alter table employment add constraint FKg403g4q35xp6hpnqstog8q45h foreign key (person_id) references user;
create table employment (id integer generated by default as identity (start with 1), key_skills varchar(255), title varchar(255), person_id integer, primary key (id));
create table user (id integer generated by default as identity (start with 1), first_name varchar(255), last_name varchar(255), primary key (id));
alter table employment add constraint FKg403g4q35xp6hpnqstog8q45h foreign key (person_id) references user;
create table employment (id integer generated by default as identity (start with 1), key_skills varchar(255), title varchar(255), person_id integer, primary key (id));
create table person (id integer generated by default as identity (start with 1), first_name varchar(255), last_name varchar(255), primary key (id));
alter table employment add constraint FK38s1rkh490imcksp8e5bv0wic foreign key (person_id) references person;
