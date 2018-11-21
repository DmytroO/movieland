create table movieland_movie (
 id                          int identity primary key
,name_russian                varchar(500)       not null
,name_native                 varchar(500)       not null
,year_of_release             int                not null
,countries                   varchar(200)       not null
,description                 varchar(4000)      not null
,rating                      decimal(3,1)
,price                       decimal(5,2)
);

create table movieland_poster (
 id                          int identity primary key
,movie_id                    int                not null
,poster_url                  varchar(1000)      not null
);
