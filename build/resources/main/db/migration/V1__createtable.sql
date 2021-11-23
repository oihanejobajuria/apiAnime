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

-- ------------------------------------------------------------------------------

CREATE TABLE users(
    usersid UUID not null default gen_random_uuid() primary key,
    username varchar(24) NOT NULL UNIQUE,
    password varchar(255) NOT NULL,
    role varchar(10),
    enabled boolean DEFAULT true
);

--CREATE EXTENSION IF NOT EXISTS pgcrypto;
--INSERT INTO users (username, password) VALUES
--    ('user', crypt('pass', gen_salt('bf')));
-- ------------------------------------------------------------------------------

CREATE TABLE file (
    fileid UUID NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    contenttype text,
    data bytea
);

