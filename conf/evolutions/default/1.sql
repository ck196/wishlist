# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table user (
  id                        bigint auto_increment not null,
  username                  varchar(255) not null,
  password                  varchar(255) not null,
  created_at                datetime(6) not null,
  updated_at                datetime(6) not null,
  constraint uq_user_username unique (username),
  constraint pk_user primary key (id))
;

create table wish (
  id                        bigint auto_increment not null,
  title                     varchar(255) not null,
  content                   TEXT,
  is_public                 tinyint(1) default 0,
  is_mark                   tinyint(1) default 0,
  has_password              tinyint(1) default 0,
  password                  varchar(255),
  constraint pk_wish primary key (id))
;


create table wish_user (
  wish_id                        bigint not null,
  user_id                        bigint not null,
  constraint pk_wish_user primary key (wish_id, user_id))
;
alter table wish add constraint fk_wish_creator_1 foreign key (id) references user (id) on delete restrict on update restrict;
create index ix_wish_creator_1 on wish (id);



alter table wish_user add constraint fk_wish_user_wish_01 foreign key (wish_id) references wish (id) on delete restrict on update restrict;

alter table wish_user add constraint fk_wish_user_user_02 foreign key (user_id) references user (id) on delete restrict on update restrict;

# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table user;

drop table wish;

drop table wish_user;

SET FOREIGN_KEY_CHECKS=1;

