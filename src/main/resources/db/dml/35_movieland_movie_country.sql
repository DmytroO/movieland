insert into movieland_movie_country (id ,movie_id ,country_id)
select nextval('movieland_movie_country_id_seq') 
,m.id
,c.id
from       movieland_movie            m
inner join movieland_country          c   on  strpos(m.countries ,c.name) != 0
left  join movieland_movie_country    mmc on  mmc.movie_id   = m.id
      and  mmc.country_id           = c.id
where  mmc.id is null
;