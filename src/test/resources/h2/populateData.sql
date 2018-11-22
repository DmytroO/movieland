insert into movieland_movie (id,name_russian,name_native,year_of_release,countries,description,rating,price
)values(
1,'Фильм1','Movie1',1998,'США','Описание1.',8.9,123.45
);
insert into movieland_movie (id,name_russian,name_native,year_of_release,countries,description,rating,price
)values(
2,'Фильм2','Movie2',1999,'США','Описание2.',9.0 ,999.99
);
insert into movieland_movie (id,name_russian,name_native,year_of_release,countries,description,rating,price
)values(
3,'Фильм3','Movie3',2000,'США','Описание3',9.9,200.00);

insert into movieland_poster (id ,movie_id ,poster_url) values (1 ,1,'picture1.jpg');
insert into movieland_poster (id ,movie_id ,poster_url) values (2 ,2,'picture2.jpeg');
insert into movieland_poster (id ,movie_id ,poster_url) values (3 ,3,'picture3.png');

insert into movieland_genre (id ,name) values (1 ,'драма');
insert into movieland_genre (id ,name) values (2 ,'триллер');
insert into movieland_genre (id ,name) values (3 ,'фэнтези');

