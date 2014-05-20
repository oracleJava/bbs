drop table if exists t_board;

/*==============================================================*/
/* Table: t_board                                               */
/*==============================================================*/
create table t_board
(
   bid                  integer not null,
   bname                char(100),
   Introduction         char(255),
   pid                  integer,
   primary key (bid)
);
alter table t_board add constraint FK_Reference_4 foreign key (pid)
      references t_board (bid) on delete restrict on update restrict;
      
drop table if exists t_user;

/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
create table t_user
(
   id                   integer not null,
   username             char(50),
   password             char(50),
   photo                char(100),
   sign                 char(255),
   level                int,
   score                double,
   regDate              timestamp,
   primary key (id)
);
      
      
drop table if exists t_post;
/*==============================================================*/
/* Table: t_post                                                */
/*==============================================================*/
create table t_post
(
   id                   integer not null,
   title                char(255),
   content              blob,
   updateDate           date,
   readNum              double,
   replyNum             double,
   uid                  integer,
   pid                  integer,
   bid                  integer,
   primary key (id)
);

alter table t_post add constraint FK_Reference_1 foreign key (pid)
      references t_post (id) on delete restrict on update restrict;

alter table t_post add constraint FK_Reference_2 foreign key (bid)
      references t_board (bid) on delete restrict on update restrict;

alter table t_post add constraint FK_Reference_3 foreign key (tid)
      references t_user (id) on delete restrict on update restrict;
      