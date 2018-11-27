create table movieland_user (
 id                          serial primary key
,email                       varchar(200) unique not null
,username                    varchar(100) unique not null
,password                    varchar(100)        not null
,role                        varchar(10)         constraint movieland_user_ck1 check (role in ('ADMIN' ,'USER'))
);