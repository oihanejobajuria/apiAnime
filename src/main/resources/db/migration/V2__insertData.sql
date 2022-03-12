--  V1 -------------------------------------------------------------------------------------

INSERT INTO anime(name, description, type, year, imageurl) values
    ('Fullmetal Alchemist', 'FMA', 'action', 2003, 'https://i.imgur.com/SRJAfPL.jpg'),
    ('Fullmetal Alchemist Brotherhood', 'FMAB', 'action', 2009, 'https://i.imgur.com/Jq0ZC2L.jpg'),
    ('Haikyuu!', 'Voley', 'deportes', 2014, 'https://i.imgur.com/4YthHz5.jpg'),
    ('Hunter X Hunter', 'HXH', 'action', 2011, 'https://i.imgur.com/XtffOmW.jpg'),
    ('Black Clover', 'BC', 'action', 2017, 'https://i.imgur.com/KkUTG7b.jpg'),
    ('Jibaku Shonen Hanako-kun', 'Hanako', 'sobrenatural', 2020, 'https://i.imgur.com/D0t8pb3.jpg'),
    ('Cardcaptor Sakura', 'Sakura', 'magia', 2000, 'https://i.imgur.com/BCWduX3.jpg');

CREATE EXTENSION IF NOT EXISTS pgcrypto;
INSERT INTO users (username, password) VALUES
    ('user', crypt('pass', gen_salt('bf'))),
    ('user2', crypt('pass', gen_salt('bf'))),
    ('user3', crypt('pass', gen_salt('bf'))),
    ('user4', crypt('pass', gen_salt('bf'))),
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
    ('action', 'img/1'),
    ('deportes', 'img/1'),
    ('sobrenatural', 'img/1'),
    ('magia', 'img/1');

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
    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist'), 'First Part', 1),
    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist'), 'Second Part', 2),
    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist'), 'Third Part', 3),
    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist'), 'Fourth Part', 4),

    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist Brotherhood'), 'First Part', 1),
    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist Brotherhood'), 'Second Part', 2),
    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist Brotherhood'), 'Third Part', 3),
    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist Brotherhood'), 'Fourth Part', 4),
    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist Brotherhood'), 'Fifth Part', 5),

    ((SELECT animeid FROM anime WHERE name='Haikyuu!'), 'First season', 1),
    ((SELECT animeid FROM anime WHERE name='Haikyuu!'), 'Second season', 2),
    ((SELECT animeid FROM anime WHERE name='Haikyuu!'), 'Third Season', 3),
    ((SELECT animeid FROM anime WHERE name='Haikyuu!'), 'To the Top', 4),

    ((SELECT animeid FROM anime WHERE name='Jibaku Shonen Hanako-kun'), 'First season', 1),

    ((SELECT animeid FROM anime WHERE name='Cardcaptor Sakura'), 'First season', 1),
    ((SELECT animeid FROM anime WHERE name='Cardcaptor Sakura'), 'Second season', 2),
    ((SELECT animeid FROM anime WHERE name='Cardcaptor Sakura'), 'Third Season', 3)
;
