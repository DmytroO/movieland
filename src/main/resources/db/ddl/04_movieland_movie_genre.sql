create table movieland_movie_genre (
 id                serial       primary key
,movie_id          integer      not null
,genre_id          integer      not null
);
alter table movieland_movie_genre 
  add constraint movieland_movie_genre_fk1 
    foreign key (movie_id)
    references movieland_movie
;
alter table movieland_movie_genre 
  add constraint movieland_movie_genre_fk2
    foreign key (genre_id)
    references movieland_genre
;


