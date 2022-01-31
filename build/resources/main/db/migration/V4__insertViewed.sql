
--  V4.3 -----------------------------------------------------------------------------------

INSERT INTO viewed VALUES
-- 'Hanako' - Episodio num 1 - Temporada num 1
    ((SELECT episodeid FROM episode WHERE seasonid = (SELECT seasonid from season where animeid =
    (select animeid from anime where name='Jibaku Shonen Hanako-kun') AND num=1) AND num=1),
        (SELECT usersid FROM users WHERE username='user')),

-- 'Haikyuu!' - Episodio num 1 - Temporada num 4
    ((SELECT episodeid FROM episode WHERE seasonid = (SELECT seasonid from season where animeid =
            (select animeid from anime where name='Haikyuu!') AND num=4) AND num=1),
        (SELECT usersid FROM users WHERE username='user')),

-- 'Haikyuu!' - Episodio num 3 - Temporada num 1
    ((SELECT episodeid FROM episode WHERE seasonid = (SELECT seasonid from season where animeid =
            (select animeid from anime where name='Haikyuu!') AND num=1) AND num=3),
        (SELECT usersid FROM users WHERE username='user'))
;


INSERT INTO followers VALUES
-- Usuario (campo 1) sigue a usuario (campo 2):
    ((SELECT usersid FROM users WHERE username = 'user'), (SELECT usersid FROM users WHERE username = 'osj'))
;


INSERT INTO watchlist(name,description, usersid) VALUES
    ('Animes de la mama', 'Estos animes le gustan mucho a mi madre, que asco animes de boomers',
        (SELECT usersid FROM users WHERE username='user')),
    ('Animes del papa', 'Estos animes le gustan mucho a mi padre, que asco animes de boomers',
        (SELECT usersid FROM users WHERE username='user')),
    ('Animes del tete', 'Estos animes le gustan mucho a mi hermano, ¿¿¿porque hay tantas chicas con uniforme???',
        (SELECT usersid FROM users WHERE username='user'))
;


--INSERT INTO watchlist_animes VALUES
--    ((SELECT watchlistid FROM watchlist WHERE name = 'Animes del tete'), (SELECT animeid FROM anime WHERE name = 'Fullmetal Alchemist Brotherhood')),
--    ((SELECT watchlistid FROM watchlist WHERE name = 'Animes del tete'), (SELECT animeid FROM anime WHERE name = 'Hunter X Hunter')),
--    ((SELECT watchlistid FROM watchlist WHERE name = 'Animes del tete'), (SELECT animeid FROM anime WHERE name = 'Black Clover'))
--;


