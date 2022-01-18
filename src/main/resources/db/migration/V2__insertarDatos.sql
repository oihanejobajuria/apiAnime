INSERT INTO anime(name, description, type, year, imageurl) values
    ('Fullmetal Alchemist', 'FMA', 'action', 2003, '/images/123'),
    ('Fullmetal Alchemist Brotherhood', 'FMAB', 'action', 2009, '/images/124 '),
    ('Haikyuu!', 'Voley', 'deportes', 2014, '/images/124 '),
    ('Hunter X Hunter', 'HXH', 'action', 2011, '/images/124 '),
    ('Black Clover', 'BC', 'action', 2017, '/images/124 '),
    ('Jibaku Shonen Hanako-kun', 'Hanako', 'sobrenatural', 2020, '/images/124 '),
    ('Cardcaptor Sakura', 'Sakura', 'sobrenatural', 2000, '/images/124 ');

CREATE EXTENSION IF NOT EXISTS pgcrypto;
INSERT INTO users (username, password) VALUES
    ('user', crypt('pass', gen_salt('bf')));

-- file

INSERT INTO authors(name, imageurl) values
    ('Hiromu Arakawa', '/images/123'),
    ('Iro Aida', '/images/123'),
    ('Haruichi Furudate', '/images/123'),
    ('Yoshihiro Togashi', '/images/123'),
    ('Yuki Tabata', '/images/123'),
    ('Iro Aida', '/images/123'),
    ('Clamp', '/images/123');

INSERT INTO anime_author VALUES
    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist'),(SELECT authorid FROM authors WHERE name='Persona 1')),
    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist Brotherhood'),(SELECT authorid FROM authors WHERE name='Persona 2'));

INSERT INTO genres(label, imageurl) values
    ('Genre 1',  'img/1'),
    ('Genre 2',  'img/1');

INSERT INTO anime_genre VALUES
    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist'),(SELECT genreid FROM genres WHERE label='Genre 1')),
    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist Brotherhood'),(SELECT genreid FROM genres WHERE label='Genre 2'));