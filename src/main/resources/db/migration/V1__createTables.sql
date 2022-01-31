--  V1 -------------------------------------------------------------------------------------
CREATE TABLE anime(
    animeid UUID NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    name text,
    description text,
    type text,
    year int,
    imageurl text
);

CREATE TABLE users(
    usersid UUID NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    username text NOT NULL UNIQUE,
    password text NOT NULL,
    role text,
    enabled boolean DEFAULT TRUE
);

CREATE TABLE file (
    fileid UUID NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    contenttype text,
    data bytea
);

--  V2 -------------------------------------------------------------------------------------

CREATE TABLE authors(
    authorid UUID NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    name text,
    imageurl text
);

CREATE TABLE anime_author(
    animeid UUID REFERENCES anime(animeid) ON DELETE CASCADE,
    authorid UUID REFERENCES authors(authorid) ON DELETE CASCADE,
    PRIMARY KEY (animeid, authorid)
);

CREATE TABLE genres(
    genreid UUID NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
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
  seasonid UUID NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
  name text,
  num int,
  animeid UUID REFERENCES anime(animeid) ON DELETE CASCADE
);

CREATE TABLE episode(
  episodeid UUID NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
  name text,
  num int,
  synopsis text,
  seasonid UUID REFERENCES season(seasonid) ON DELETE CASCADE
);

CREATE TABLE viewed(
  episodeid UUID REFERENCES episode(episodeid) ON DELETE CASCADE,
  usersid UUID REFERENCES users(usersid) ON DELETE CASCADE,
  PRIMARY KEY (episodeid, usersid)
);

CREATE TABLE followers(
  userbase UUID REFERENCES users(usersid) ON DELETE CASCADE,
  followers_list UUID REFERENCES users(usersid) ON DELETE CASCADE,
  PRIMARY KEY (userbase, followers_list)
);


CREATE TABLE watchlist(
  watchlistid UUID NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
  name text,
  description text,
  usersid UUID REFERENCES users(usersid) ON DELETE CASCADE
);


CREATE TABLE watchlist_animes (
  animeid UUID REFERENCES anime(animeid) ON DELETE CASCADE,
  watchlistid UUID REFERENCES watchlist(watchlistid) ON DELETE CASCADE,
  PRIMARY KEY (animeid, watchlistid)
);
