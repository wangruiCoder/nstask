drop database if exists task;

/*==============================================================*/
/* Database: task                                               */
/*==============================================================*/
create database task;

use task;

/*==============================================================*/
/* Table: T_SYS_USER                                            */
/*==============================================================*/
create table T_SYS_USER
(
   USER_ID              integer not null auto_increment comment '用户ID',
   USER_NAME            char(30) comment '用户登录名',
   PASSWORD             char(30) comment '登录密码',
   ERR_LOGIN_COUNT      int comment '错误登录次数',
   LAST_EDIT_PASSWORD_TIME datetime comment '上一次修改面的时间',
   ACCOUNT_STATUE       int comment '用户状态',
   CREATOR_ID           integer comment '创建者ID',
   CREATE_TIME          datetime comment '创建时间',
   MODIFIER_ID          integer comment '修改者id',
   MODIFIER_TIME        datetime comment '修改时间',
   DELETE_ID            integer comment '删除者id',
   DELETE_TIME          datetime comment '删除时间',
   primary key (USER_ID)
);
