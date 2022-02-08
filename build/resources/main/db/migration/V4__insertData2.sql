
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
    ((SELECT usersid FROM users WHERE username = 'user'), (SELECT usersid FROM users WHERE username = 'osj')),
    ((SELECT usersid FROM users WHERE username = 'user'), (SELECT usersid FROM users WHERE username = 'sdr')),

    ((SELECT usersid FROM users WHERE username = 'user2'), (SELECT usersid FROM users WHERE username = 'user')),
    ((SELECT usersid FROM users WHERE username = 'user3'), (SELECT usersid FROM users WHERE username = 'user')),
    ((SELECT usersid FROM users WHERE username = 'user4'), (SELECT usersid FROM users WHERE username = 'user'))
;


INSERT INTO watchlist(name,description, usersid) VALUES
-- Listras creadas por el usuario sdr
    ('Animes de la mama', 'Estos animes le gustan mucho a mi madre, que asco animes de boomers',
        (SELECT usersid FROM users WHERE username='sdr')),
    ('Animes del papa', 'Estos animes le gustan mucho a mi padre, que asco animes de boomers',
        (SELECT usersid FROM users WHERE username='sdr')),
    ('Animes del tete', 'Estos animes le gustan mucho a mi hermano, ¿¿¿porque hay tantas chicas con uniforme???',
        (SELECT usersid FROM users WHERE username='sdr')),

-- Listras creadas por el usuario user
    ('Tienen remake', 'Estos animes son los que tienen remake, ya sean originales o el propio remake',
        (SELECT usersid FROM users WHERE username='user')),
    ('De la infacia', 'Estos animes los veia de pequenya',
        (SELECT usersid FROM users WHERE username='user')),
    ('Leo el manga tambien', 'Estos animes los empece viendo y ahora me estoy leyendo el manga, menudo vicio',
        (SELECT usersid FROM users WHERE username='user'))
;


INSERT INTO watchlist_animes VALUES
-- Lista 'Animes del tete' - animes anyadidos
    ((SELECT watchlistid FROM watchlist WHERE name = 'Animes del tete'), (SELECT animeid FROM anime WHERE name = 'Fullmetal Alchemist Brotherhood')),
    ((SELECT watchlistid FROM watchlist WHERE name = 'Animes del tete'), (SELECT animeid FROM anime WHERE name = 'Hunter X Hunter')),
    ((SELECT watchlistid FROM watchlist WHERE name = 'Animes del tete'), (SELECT animeid FROM anime WHERE name = 'Black Clover')),

-- Lista 'Tienen remake' - animes anyadidos
    ((SELECT watchlistid FROM watchlist WHERE name = 'Tienen remake'), (SELECT animeid FROM anime WHERE name = 'Fullmetal Alchemist')),
    ((SELECT watchlistid FROM watchlist WHERE name = 'Tienen remake'), (SELECT animeid FROM anime WHERE name = 'Fullmetal Alchemist Brotherhood')),
    ((SELECT watchlistid FROM watchlist WHERE name = 'Tienen remake'), (SELECT animeid FROM anime WHERE name = 'Hunter X Hunter')),

-- Lista 'De la infacia' - animes anyadidos
    ((SELECT watchlistid FROM watchlist WHERE name = 'De la infacia'), (SELECT animeid FROM anime WHERE name = 'Cardcaptor Sakura')),

-- Lista 'Leo el manga tambien' - animes anyadidos
    ((SELECT watchlistid FROM watchlist WHERE name = 'Leo el manga tambien'), (SELECT animeid FROM anime WHERE name = 'Haikyuu!')),
    ((SELECT watchlistid FROM watchlist WHERE name = 'Leo el manga tambien'), (SELECT animeid FROM anime WHERE name = 'Black Clover')),
    ((SELECT watchlistid FROM watchlist WHERE name = 'Leo el manga tambien'), (SELECT animeid FROM anime WHERE name = 'Jibaku Shonen Hanako-kun'))
;


