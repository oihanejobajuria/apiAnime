CREATE TABLE anime(
    animeid UUID not null default gen_random_uuid() primary key,
    name text,
    description text,
    type text,
    year int,
    imageurl text
);

INSERT INTO anime(name, description, type, year, imageurl) values
    ('Fullmetal Alchemist', 'FMA', 'action', 2009, '/images/123');