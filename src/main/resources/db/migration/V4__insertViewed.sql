-- V4 -----------------------

-- Viewed:

INSERT INTO viewed VALUES
-- 'Hanako' - Episodio num 1 - Temporada num 1
    ((SELECT episodeid FROM episode WHERE seasonid = (SELECT seasonid from season where animeid =
    (select animeid from anime where name='Jibaku Shonen Hanako-kun') AND num=1) AND num=1),
        (SELECT usersid FROM users WHERE username='user')),

-- 'Haikyuu!' - Episodio num 1 - Temporada num 4
    ((SELECT episodeid FROM episode WHERE seasonid = (SELECT seasonid from season where animeid =
            (select animeid from anime where name='Haikyuu!') AND num=4) AND num=1),
        (SELECT usersid FROM users WHERE username='user')),

-- 'Haikyuu!' - Episodio num 3 - Temporada num 1
    ((SELECT episodeid FROM episode WHERE seasonid = (SELECT seasonid from season where animeid =
            (select animeid from anime where name='Haikyuu!') AND num=1) AND num=3),
        (SELECT usersid FROM users WHERE username='user'))
;