\pset pager off
\o 01_movieland_genre.sql
select 'insert into movieland_genre (id ,name) values (' || id || ' ,''' || replace(name ,'''' ,'''''') || ''');' from movieland_genre;
\o
\o 02_movieland_user.sql
select 'insert into movieland_user (id ,email ,username ,password ,role) values ('
|| id || ' ,'''
|| replace(email ,'''' ,'''''') || ''' ,''' 
|| replace(username ,'''' ,'''''') || ''' ,''' 
|| replace(password ,'''' ,'''''') || ''' ,''' 
|| replace(role ,'''' ,'''''') ||''');' 
from movieland_user;
\o
\o 03_movieland_movie.sql
select 'insert into movieland_user (id,name_russian,name_native,year_of_release,countries,description,rating,price)values(' || id || ','''
|| replace(name_russian ,'''' ,'''''') || ''',''' 
|| replace(name_native  ,'''' ,'''''') || ''',' 
|| year_of_release || ',''' 
|| replace(countries    ,'''' ,'''''') || ''',''' 
|| replace(description  ,'''' ,'''''') || ''',' 
|| rating || ',' 
|| price ||');'
from    movieland_movie
;
\o
\o 04_movieland_movie_genre.sql
select 'insert into movieland_movie_genre (id ,movie_id ,genre_id) values (' || id || ' ,' || movie_id || ' ,' || genre_id || ');' 
from    movieland_movie_genre
;
\o
\o 05_movieland_poster.sql
select 'insert into movieland_poster (id ,movie_id ,poster_url) values (' || id || ' ,' || movie_id || ',''' || replace(poster_url ,'''' ,'''''') || ''');'
from    movieland_poster
;
\o
\o 06_movieland_review.sql
select 'insert into movieland_review (id ,movie_id ,user_id ,review) values (' || id || ' ,' || movie_id || ' ,' || user_id || ' ,''' || replace(review ,'''' ,'''''') || ''');' 
from    movieland_review
;
\o