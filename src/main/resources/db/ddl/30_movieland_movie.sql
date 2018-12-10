create table if not exists movieland_movie (
 id                          serial primary key
,name_russian                varchar(500)       not null
,name_native                 varchar(500)       not null
,year_of_release             numeric(4)         not null
,countries                   varchar(200)       not null
,description                 varchar(8000)
,rating                      numeric(10,9)
,price                       numeric(6,2)
);
alter table movieland_movie
  drop constraint if exists movieland_movie_uk1
;
alter table movieland_movie 
  add constraint movieland_movie_uk1 unique (
    name_russian ,name_native ,countries
  )
;