drop table if exists v1.attendee cascade;
drop table if exists v1.meeting cascade;
drop sequence if exists attendee_seq;
drop sequence if exists meeting_seq;
create table v1.attendee (
    id bigint not null auto_increment,
    meeting_id bigint,
    email varchar(255),
    name varchar(255),
    phone varchar(255),
    primary key (id)
);
create table v1.meeting (
    created_at timestamp(6),
    id bigint not null auto_increment,
    updated_at timestamp(6),
    description varchar(255),
    name varchar(255),
    primary key (id)
);
create sequence attendee_seq start with 1 increment by 50;
create sequence meeting_seq start with 1 increment by 50;
alter table if exists v1.attendee
   add constraint FKemar4h6qsc4n36fnrtgjiyu1c
   foreign key (meeting_id)
   references v1.meeting;