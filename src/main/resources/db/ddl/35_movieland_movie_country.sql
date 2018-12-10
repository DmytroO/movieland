create table if not exists movieland_movie_country (
 id                serial       primary key
,movie_id          integer                  not null
,country_id        integer                  not null
);

alter table movieland_movie_country
  drop constraint if exists movieland_movie_country_fk1 
;
alter table movieland_movie_country
  add constraint movieland_movie_country_fk1 
    foreign key (movie_id)
    references movieland_movie
;
alter table movieland_movie_country
  drop constraint if exists movieland_movie_country_fk2
;
alter table movieland_movie_country
  add constraint movieland_movie_country_fk2
    foreign key (country_id)
    references movieland_country
;
