select r.id ,r.user_id ,r.review
from       movieland_review    r
where  r.movie_id = ?