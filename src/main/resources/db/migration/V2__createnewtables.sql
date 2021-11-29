-- nuevo ejemplo

CREATE TABLE doblador(
    dobladorid UUID not null default gen_random_uuid() primary key,
    name text,
    imageurl text
);

--INSERT INTO anime(name, description, type, year, imageurl) values
--    ('Fullmetal Alchemist', 'FMA', 'action', 2009, '/images/123'),
--    ('Fullmetal Alchemist Brotherhood', 'FMAB', 'action', 2011, '/images/124 ');

-- ------------------------------------------------------------------------------

CREATE TABLE anime_doblador(
    animeid UUID REFERENCES anime(animeid) ON DELETE CASCADE,
    dobladorid UUID REFERENCES doblador(dobladorid) ON DELETE CASCADE,
    PRIMARY KEY (animeid, dobladorid)
);