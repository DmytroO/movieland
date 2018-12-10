create table if not exists movieland_genre (
 id                serial       primary key
,name              varchar(100) unique      not null
);

