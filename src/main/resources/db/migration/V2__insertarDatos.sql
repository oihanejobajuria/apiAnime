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
    ('osj', crypt('pass', gen_salt('bf')));

-- file

--  V2 -------------------------------------------------------------------------------------

INSERT INTO authors(name, imageurl) values
    ('Hiromu Arakawa', '/images/123'),
    -- ('Hiromu Arakawa', '/images/123'), -- fmab = fma
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
    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist'),(SELECT genreid FROM genres WHERE label='Genre 1')),
    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist Brotherhood'),(SELECT genreid FROM genres WHERE label='Genre 2')),
    ((SELECT animeid FROM anime WHERE name='Haikyuu!'),(SELECT genreid FROM genres WHERE label='Genre 2')),
    ((SELECT animeid FROM anime WHERE name='Hunter X Hunter'),(SELECT genreid FROM genres WHERE label='Genre 2')),
    ((SELECT animeid FROM anime WHERE name='Black Clover'),(SELECT genreid FROM genres WHERE label='Genre 2')),
    ((SELECT animeid FROM anime WHERE name='Jibaku Shonen Hanako-kun'),(SELECT genreid FROM genres WHERE label='Genre 2')),
    ((SELECT animeid FROM anime WHERE name='Cardcaptor Sakura'),(SELECT genreid FROM genres WHERE label='Genre 2'));

--  V3 -------------------------------------------------------------------------------------

INSERT INTO anime_genre VALUES
    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist'),(SELECT userid FROM users WHERE name='user')),
    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist Brotherhood'),(SELECT userid FROM users WHERE name='user')),
    ((SELECT animeid FROM anime WHERE name='Haikyuu!'),(SELECT userid FROM users WHERE name='user')),
    ((SELECT animeid FROM anime WHERE name='Hunter X Hunter'),(SELECT userid FROM users WHERE name='user')),
    ((SELECT animeid FROM anime WHERE name='Black Clover'),(SELECT userid FROM users WHERE name='user')),
    ((SELECT animeid FROM anime WHERE name='Jibaku Shonen Hanako-kun'),(SELECT userid FROM users WHERE name='user')),
    ((SELECT animeid FROM anime WHERE name='Cardcaptor Sakura'),(SELECT userid FROM users WHERE name='user'));
