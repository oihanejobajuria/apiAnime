--  V1 -------------------------------------------------------------------------------------

INSERT INTO anime(name, description, type, year, imageurl) values
    ('Fullmetal Alchemist', 'FMA', 'action', 2003, '/images/123'),
    ('Fullmetal Alchemist Brotherhood', 'FMAB', 'action', 2009, '/images/124 '),
    ('Haikyuu!', 'Voley', 'deportes', 2014, '/images/124 '),
    ('Hunter X Hunter', 'HXH', 'action', 2011, '/images/124 '),
    ('Black Clover', 'BC', 'action', 2017, '/images/124 '),
    ('Jibaku Shonen Hanako-kun', 'Hanako', 'sobrenatural', 2020, '/images/124 '),
    ('Cardcaptor Sakura', 'Sakura', 'magia', 2000, '/images/124 ');

CREATE EXTENSION IF NOT EXISTS pgcrypto;
INSERT INTO users (username, password) VALUES
    ('user', crypt('pass', gen_salt('bf'))),
    ('osj', crypt('pass', gen_salt('bf'))),
    ('sdr', crypt('pass', gen_salt('bf')));

-- file

--  V2 -------------------------------------------------------------------------------------

INSERT INTO authors(name, imageurl) values
    ('Hiromu Arakawa', '/images/123'),
    ('Haruichi Furudate', '/images/123'),
    ('Yoshihiro Togashi', '/images/123'),
    ('Yuki Tabata', '/images/123'),
    ('Iro Aida', '/images/123'),
    ('Clamp', '/images/123');

INSERT INTO anime_author VALUES
    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist'),(SELECT authorid FROM authors WHERE name='Hiromu Arakawa')),
    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist Brotherhood'),(SELECT authorid FROM authors WHERE name='Hiromu Arakawa')),
    ((SELECT animeid FROM anime WHERE name='Haikyuu!'),(SELECT authorid FROM authors WHERE name='Haruichi Furudate')),
    ((SELECT animeid FROM anime WHERE name='Hunter X Hunter'),(SELECT authorid FROM authors WHERE name='Yoshihiro Togashi')),
    ((SELECT animeid FROM anime WHERE name='Black Clover'),(SELECT authorid FROM authors WHERE name='Yuki Tabata')),
    ((SELECT animeid FROM anime WHERE name='Jibaku Shonen Hanako-kun'),(SELECT authorid FROM authors WHERE name='Iro Aida')),
    ((SELECT animeid FROM anime WHERE name='Cardcaptor Sakura'),(SELECT authorid FROM authors WHERE name='Clamp'));

INSERT INTO genres(label, imageurl) values
    ('action',  'img/1'),
    ('deportes',  'img/1'),
    ('sobrenatural',  'img/1'),
    ('magia',  'img/1');

INSERT INTO anime_genre VALUES
    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist'),(SELECT genreid FROM genres WHERE label='action')),
    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist Brotherhood'),(SELECT genreid FROM genres WHERE label='action')),
    ((SELECT animeid FROM anime WHERE name='Haikyuu!'),(SELECT genreid FROM genres WHERE label='deportes')),
    ((SELECT animeid FROM anime WHERE name='Hunter X Hunter'),(SELECT genreid FROM genres WHERE label='action')),
    ((SELECT animeid FROM anime WHERE name='Black Clover'),(SELECT genreid FROM genres WHERE label='action')),
    ((SELECT animeid FROM anime WHERE name='Jibaku Shonen Hanako-kun'),(SELECT genreid FROM genres WHERE label='sobrenatural')),
    ((SELECT animeid FROM anime WHERE name='Cardcaptor Sakura'),(SELECT genreid FROM genres WHERE label='magia'));

--  V3 -------------------------------------------------------------------------------------

INSERT INTO favorite VALUES
    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist'),(SELECT usersid FROM users WHERE username='user')),
    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist Brotherhood'),(SELECT usersid FROM users WHERE username='user')),
    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist Brotherhood'),(SELECT usersid FROM users WHERE username='sdr')),
--    ((SELECT animeid FROM anime WHERE name='Haikyuu!'),(SELECT usersid FROM users WHERE username='user')),
--    ((SELECT animeid FROM anime WHERE name='Hunter X Hunter'),(SELECT usersid FROM users WHERE username='user')),
--    ((SELECT animeid FROM anime WHERE name='Black Clover'),(SELECT usersid FROM users WHERE username='user')),
    ((SELECT animeid FROM anime WHERE name='Jibaku Shonen Hanako-kun'),(SELECT usersid FROM users WHERE username='osj')),
    ((SELECT animeid FROM anime WHERE name='Cardcaptor Sakura'),(SELECT usersid FROM users WHERE username='osj'));

--  V4 -------------------------------------------------------------------------------------

INSERT INTO season(animeid, name, num) VALUES
    ((SELECT animeid FROM anime WHERE name='Haikyuu!'), 'First Season', 1),
    ((SELECT animeid FROM anime WHERE name='Haikyuu!'), 'Second season', 2),
    ((SELECT animeid FROM anime WHERE name='Haikyuu!'), 'Third Season', 3),
    ((SELECT animeid FROM anime WHERE name='Haikyuu!'), 'To the Top', 4),
    ((SELECT animeid FROM anime WHERE name='Jibaku Shonen Hanako-kun'), 'First Season', 1);

INSERT INTO episode(seasonid, name, num, synopsis) VALUES
--'Haikyuu' - 2 episodios por temporada
    ((SELECT seasonid from season where animeid = (select animeid from anime where name='Haikyuu!') AND num=1),
        'Episodio1', 1, 'Lorem ipsum dolor sit amet'),
    ((SELECT seasonid from season where animeid = (select animeid from anime where name='Haikyuu!') AND num=1),
        'Episodio2', 2, 'Lorem ipsum dolor sit amet'),
    ((SELECT seasonid from season where animeid = (select animeid from anime where name='Haikyuu!') AND num=2),
        'Episodio1', 1, 'Lorem ipsum dolor sit amet'),
    ((SELECT seasonid from season where animeid = (select animeid from anime where name='Haikyuu!') AND num=2),
        'Episodio2', 2, 'Lorem ipsum dolor sit amet'),
    ((SELECT seasonid from season where animeid = (select animeid from anime where name='Haikyuu!') AND num=3),
        'Episodio1', 1, 'Lorem ipsum dolor sit amet'),
    ((SELECT seasonid from season where animeid = (select animeid from anime where name='Haikyuu!') AND num=3),
        'Episodio2', 2, 'Lorem ipsum dolor sit amet'),
    ((SELECT seasonid from season where animeid = (select animeid from anime where name='Haikyuu!') AND num=4),
        'Episodio1', 1, 'Lorem ipsum dolor sit amet'),
    ((SELECT seasonid from season where animeid = (select animeid from anime where name='Haikyuu!') AND num=4),
        'Episodio2', 2, 'Lorem ipsum dolor sit amet '),

--'Hanako-kun' - 12 episodios 1 temporada
    ((SELECT seasonid from season where animeid = (select animeid from anime where name='Jibaku Shonen Hanako-kun') AND num=1),
        'Aparición 1: Hanako-san de los Baños', 1, 'Lorem ipsum dolor sit amet'),
    ((SELECT seasonid from season where animeid = (select animeid from anime where name='Jibaku Shonen Hanako-kun') AND num=1),
        'Aparición 2: Yousei-san', 2, 'Lorem ipsum dolor sit amet'),
    ((SELECT seasonid from season where animeid = (select animeid from anime where name='Jibaku Shonen Hanako-kun') AND num=1),
        'Aparición 3: Las Escaleras Misaki, Parte 1', 3, 'Lorem ipsum dolor sit amet'),
    ((SELECT seasonid from season where animeid = (select animeid from anime where name='Jibaku Shonen Hanako-kun') AND num=1),
        'Aparición 3: Las Escaleras Misaki, Parte 2', 4, 'Lorem ipsum dolor sit amet'),
    ((SELECT seasonid from season where animeid = (select animeid from anime where name='Jibaku Shonen Hanako-kun') AND num=1),
        'Aparición 5: El Árbol de la Confesión', 5, 'Lorem ipsum dolor sit amet'),
    ((SELECT seasonid from season where animeid = (select animeid from anime where name='Jibaku Shonen Hanako-kun') AND num=1),
        'Aparición 6: La Biblioteca de las 4 en Punto', 6, 'Lorem ipsum dolor sit amet'),
    ((SELECT seasonid from season where animeid = (select animeid from anime where name='Jibaku Shonen Hanako-kun') AND num=1),
        'Aparición 7: Donas', 7, 'Lorem ipsum dolor sit amet'),
    ((SELECT seasonid from season where animeid = (select animeid from anime where name='Jibaku Shonen Hanako-kun') AND num=1),
        'Aparición 8: Mitsuba', 8, 'Lorem ipsum dolor sit amet'),
    ((SELECT seasonid from season where animeid = (select animeid from anime where name='Jibaku Shonen Hanako-kun') AND num=1),
        'Aparición 9: La Fiesta del te', 9, 'Lorem ipsum dolor sit amet'),
    ((SELECT seasonid from season where animeid = (select animeid from anime where name='Jibaku Shonen Hanako-kun') AND num=1),
        'Aparición 10: El Infierno de los Espejos, Parte 1', 10, 'Lorem ipsum dolor sit amet'),
    ((SELECT seasonid from season where animeid = (select animeid from anime where name='Jibaku Shonen Hanako-kun') AND num=1),
        'Aparición 10: El Infierno de los Espejos, Parte 2', 11, 'Lorem ipsum dolor sit amet'),
    ((SELECT seasonid from season where animeid = (select animeid from anime where name='Jibaku Shonen Hanako-kun') AND num=1),
        'Aparición 12: La sirenita', 12, 'Lorem ipsum dolor sit amet 2')
;

INSERT INTO viewed VALUES
--'Aparición 1: Hanako-san de los Baños'  - Temporada num 1 - 'Hanako'
    ((SELECT episodeid FROM episode WHERE seasonid = (SELECT seasonid from season where animeid =
            (select animeid from anime where name='Jibaku Shonen Hanako-kun') AND num=1) AND num=1),
        (SELECT usersid FROM users WHERE username='user')),

--'Episodio1'  - Temporada num 4 - 'Haikyuu!'
    ((SELECT episodeid FROM episode WHERE seasonid = (SELECT seasonid from season where animeid =
            (select animeid from anime where name='Haikyuu!') AND num=4) AND num=1),
        (SELECT usersid FROM users WHERE username='user')),

--'Episodio3'  - Temporada num 1 - 'Haikyuu!'
    ((SELECT episodeid FROM episode WHERE seasonid = (SELECT seasonid from season where animeid =
            (select animeid from anime where name='Haikyuu!') AND num=1) AND num=3),
        (SELECT usersid FROM users WHERE username='user')),
;