create table if not exists movieland_review (
 id                          serial     primary key
,movie_id                    integer                not null
,user_id                     integer                not null
,review                      varchar(12000)         not null
);
alter table movieland_review 
  drop constraint if exists movieland_review_fk1 
;
alter table movieland_review 
  add constraint movieland_review_fk1 
    foreign key (movie_id)
    references movieland_movie
;
alter table movieland_review
  drop constraint if exists movieland_review_fk2
;
alter table movieland_review
  add constraint movieland_review_fk2
    foreign key (user_id)
    references movieland_user
;
