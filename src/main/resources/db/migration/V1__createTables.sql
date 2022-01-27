--  V1 -------------------------------------------------------------------------------------
CREATE TABLE anime(
    animeid UUID not null default gen_random_uuid() primary key,
    name text,
    description text,
    type text,
    year int,
    imageurl text
);

CREATE TABLE users(
    usersid UUID not null default gen_random_uuid() primary key,
    username varchar(24) NOT NULL UNIQUE,
    password varchar(255) NOT NULL,
    role varchar(10),
    enabled boolean DEFAULT true
);

CREATE TABLE file (
    fileid UUID NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    contenttype text,
    data bytea
);

--  V2 -------------------------------------------------------------------------------------

CREATE TABLE authors(
    authorid UUID not null default gen_random_uuid() primary key,
    name text,
    imageurl text
);

CREATE TABLE anime_author(
    animeid UUID REFERENCES anime(animeid) ON DELETE CASCADE,
    authorid UUID REFERENCES authors(authorid) ON DELETE CASCADE,
    PRIMARY KEY (animeid, authorid)
);

CREATE TABLE genres(
    genreid UUID not null default gen_random_uuid() primary key,
    label text,
    imageurl text
);

CREATE TABLE anime_genre(
    animeid UUID REFERENCES anime(animeid) ON DELETE CASCADE,
    genreid UUID REFERENCES genres(genreid) ON DELETE CASCADE,
    PRIMARY KEY (animeid, genreid)
);

--  V3 -------------------------------------------------------------------------------------

CREATE TABLE favorite(
  animeid UUID REFERENCES anime(animeid) ON DELETE CASCADE,
  usersid UUID REFERENCES users(usersid) ON DELETE CASCADE,
  PRIMARY KEY (animeid, usersid)
);

--  V4 -------------------------------------------------------------------------------------

CREATE TABLE season(
  seasonid UUID not null default gen_random_uuid() primary key,
  name text,
  num int,
  animeid UUID REFERENCES anime(animeid) ON DELETE CASCADE
);

CREATE TABLE episode(
  episodeid UUID not null default gen_random_uuid() primary key,
  name text,
  num int,
  synopsis text,
  seasonid UUID REFERENCES season(seasonid) ON DELETE CASCADE
);