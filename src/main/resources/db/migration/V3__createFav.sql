-- nuevo ejemplo

CREATE TABLE favorites(
  animeid UUID REFERENCES anime(animeid) ON DELETE CASCADE,
  usersid UUID REFERENCES authors(usersid) ON DELETE CASCADE,
  PRIMARY KEY (animeid, usersid)
);

INSERT INTO anime_author VALUES
  ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist'),(SELECT usersid FROM authors WHERE name='Persona 1')),
  ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist Brotherhood'),(SELECT usersid FROM authors WHERE name='Persona 2'));