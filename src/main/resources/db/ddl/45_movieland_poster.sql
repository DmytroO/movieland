create table if not exists movieland_poster (
 id                          serial primary key
,movie_id                    integer            not null
,poster_url                  varchar(1000)      not null 
);
alter table movieland_poster
  drop constraint if exists movieland_poster_fk1
;
alter table movieland_poster
  add constraint movieland_poster_fk1
    foreign key (movie_id)
    references movieland_movie
;