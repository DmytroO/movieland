select c.id ,c.name
from       movieland_movie_country    mc
inner join movieland_country          c   on   c.id = mc.country_id
where   mc.movie_id = ?
;
select mg.genre_id ,g.name
from       movieland_movie_genre      mg
inner join movieland_genre            g   on  g.id = mg.genre_id
where  mg.movie_id = ?
order by mg.id