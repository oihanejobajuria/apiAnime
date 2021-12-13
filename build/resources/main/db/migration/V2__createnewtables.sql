-- nuevo ejemplo

CREATE TABLE authors(
    authorid UUID not null default gen_random_uuid() primary key,
    name text,
    imageurl text
);

INSERT INTO authors(name, imageurl) values
    ('Persona 1', '/images/123'),
    ('Persona 2', '/images/123');

-- ------------------------------------------------------------------------------

CREATE TABLE anime_author(
    animeid UUID REFERENCES anime(animeid) ON DELETE CASCADE,
    authorid UUID REFERENCES authors(authorid) ON DELETE CASCADE,
    PRIMARY KEY (animeid, authorid)
);

INSERT INTO anime_author VALUES
    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist'),(SELECT authorid FROM authors WHERE name='Persona 1')),
    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist Brotherhood'),(SELECT authorid FROM authors WHERE name='Persona 2'));

-- *******************************************************************************************************************************************88

CREATE TABLE genres(
    genreid UUID not null default gen_random_uuid() primary key,
    label text,
    imageurl text
);

INSERT INTO genres(label, imageurl) values
    ('Genre 1',  'img/1'),
    ('Genre 2',  'img/1');

-- ------------------------------------------------------------------------------

CREATE TABLE anime_genre(
    animeid UUID REFERENCES anime(animeid) ON DELETE CASCADE,
    genreid UUID REFERENCES genres(genreid) ON DELETE CASCADE,
    PRIMARY KEY (animeid, genreid)
);

INSERT INTO anime_genre VALUES
    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist'),(SELECT genreid FROM genres WHERE label='Genre 1')),
    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist Brotherhood'),(SELECT genreid FROM genres WHERE label='Genre 2'));