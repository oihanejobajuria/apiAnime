--  V1 -------------------------------------------------------------------------------------

INSERT INTO anime(name, description, type, year, imageurl) values
    ('Fullmetal Alchemist',
        'Tras perder a su madre, Alphonse y Edward Elric tratan de revivirla usando la ciencia prohibida de la alquimia humana. Sin embargo, la alquimia opera bajo la teoría del cambio equivalente y romper el tabú de la alquimia humana acarrea un alto precio. Ed pierde una de sus piernas y Al su cuerpo. Ed logra sellar el alma de Al dentro de una gran armadura, a costa de su brazo. Años después, Ed (ahora con dos extremidades mecánicas) y Al (aún atrapado en la armadura) dejan su casa de la niñez, cada uno preocupado por la felicidad del otro. Ed, quien tuvo un talento y habilidad natural para la alquimia, se convierte en un alquimista con certificado nacional y pronto es conocido en todas partes como el ?Alquimista Fullmetal?. El verdadero objetivo de ambos hermanos es buscar cualquier información sobre la legendaria Piedra Filosofal, con la esperanza de que ésta les permita recobrar sus cuerpos. Todas sus esperanzas descansan en la mítica piedra, que incluso puede no existir.',
        'TV', 2003, 'https://i.imgur.com/SRJAfPL.jpg'),
    ('Fullmetal Alchemist Brotherhood',
        'La historia se centra en dos hermanos, Edward Elric y Alphonse Elric que rompieron el mayor tabú de la alquimia, la trasmutación humana al tratar de revivir a su fallecida madre; en consecuencia Edward pierde su pierna izquierda y Alphonse pierde todo su cuerpo, Edward para salvar a su hermano sella su alma en una gran armadura de hierro a cambio de su brazo derecho; ahora los dos con un nuevo objetivo buscan desesperadamente la piedra filosofal para poder regresar sus cuerpos a la normalidad...',
        'TV', 2009, 'https://i.imgur.com/Jq0ZC2L.jpg'),
    ('Haikyuu!',
        'Hinata Shouyou, al ver un partido de voleibol, pretende convertirse en "El pequeño gigante", y se une a la escuela media de voleibol del club. Después de encontrar nuevos miembros, se establece para el torneo de la escuela secundaria, donde se cruzan con una escuela superior, con el "Rey del juego superior", Kageyama Tobio .Aunque su equipo perdió, Shouyou sigue decidido a seguir adelante y vengarse de Kageyama. Al entrar en la escuela secundaria, recibió la sorpresa más grande - que Kageyama se encuentran en la misma escuela y el club!',
        'TV', 2014, 'https://i.imgur.com/4YthHz5.jpg'),
    ('Hunter X Hunter',
        'Gon Frecks,un niño de 12 años,vive con su tia Mito y su abuela en Isla Ballena, huérfano de madre y según su tía también de padre. Un día Gon es atacado por un oso del bosque, pero es salvado por un cazador quien le explica que está en busca de su maestro para así convertirse en un cazador, el es quien le dice que su padre Gin esta vivo. Enterado de esto Gon decide convertirse en cazador para poder encontrar a su padre.',
        'TV', 2011, 'https://i.imgur.com/XtffOmW.jpg'),
    ('Black Clover',
        'La historia de Black Clover nos pone en la piel de un joven llamado Asta, el cual quiere convertirse en el mago más grande del reino. Sin embargo, hay un inconveniente: ¡no puede usar magia! Por suerte, Asta recibe el grimorio trébol de cinco hojas, que le otorga el poder de la anti-magia. ¿Puede alguien carente de magia convertirse en el Rey Hechicero? Una cosa está clara, Asta nunca se rendirá.',
        'TV', 2017, 'https://i.imgur.com/KkUTG7b.jpg'),
    ('Jibaku Shonen Hanako-kun',
        '«Hanako-san, Hanako-san… ¿estás ahí?» En la Academia Kamome, abundan los rumores sobre los llamados Siete Misterios de la escuela, uno de los cuales es Hanako-san. Se dice que si utilizas el tercer puesto del baño de mujeres del tercer piso en el viejo edificio de la escuela, Hanako-san te concederá cualquier deseo una vez la invoques. Nene Yashiro, una chica de preparatoria y estudiante de la Academia amante del ocultismo, se aventura a este baño encantado… ¡pero el Hanako-san que todos creían no tiene nada que ver con lo que imaginaban! ¡Resulta que Hanako-san en la Academia Kamome… es un niño!',
        'TV', 2020, 'https://i.imgur.com/D0t8pb3.jpg'),
    ('Cardcaptor Sakura',
        'Sakura Kinomoto es una niña de 10 años que vive con su padre Fujitaka y su hermano Touya. La historia comienza cuando Sakura abre un extraño libro llamado The Clow y libera accidentalmente las cartas que este contenía, Sakura deberá pasar grandes apuros para sellar las cartas pero con ayuda de sus amigos Tomoyo, Shaoran y Kerberos vivirían grandes aventuras para lograr su objetivo, atrapar todas las cartas Clow.',
        'TV', 2000, 'https://i.imgur.com/BCWduX3.jpg');

CREATE EXTENSION IF NOT EXISTS pgcrypto;
INSERT INTO users (username, password) VALUES
    ('user', crypt('pass', gen_salt('bf'))),
    ('user2', crypt('pass', gen_salt('bf'))),
    ('user3', crypt('pass', gen_salt('bf'))),
    ('user4', crypt('pass', gen_salt('bf'))),
    ('osj', crypt('pass', gen_salt('bf'))),
    ('sdr', crypt('pass', gen_salt('bf')));

-- file

--  V2 -------------------------------------------------------------------------------------

INSERT INTO authors(name, imageurl) values
    ('Hiromu Arakawa', '/images/123'),
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
    ('Sounen', 'img/1'),
    ('Deportes', 'img/1'),
    ('Sobrenatural', 'img/1'),
    ('Magia', 'img/1');

INSERT INTO anime_genre VALUES
    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist'),(SELECT genreid FROM genres WHERE label='Sounen')),
    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist Brotherhood'),(SELECT genreid FROM genres WHERE label='Sounen')),
    ((SELECT animeid FROM anime WHERE name='Haikyuu!'),(SELECT genreid FROM genres WHERE label='Deportes')),
    ((SELECT animeid FROM anime WHERE name='Hunter X Hunter'),(SELECT genreid FROM genres WHERE label='Sounen')),
    ((SELECT animeid FROM anime WHERE name='Black Clover'),(SELECT genreid FROM genres WHERE label='Sounen')),
    ((SELECT animeid FROM anime WHERE name='Jibaku Shonen Hanako-kun'),(SELECT genreid FROM genres WHERE label='Sobrenatural')),
    ((SELECT animeid FROM anime WHERE name='Cardcaptor Sakura'),(SELECT genreid FROM genres WHERE label='Magia'));

--  V3 -------------------------------------------------------------------------------------

INSERT INTO favorite VALUES
    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist'),(SELECT usersid FROM users WHERE username='user')),
    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist Brotherhood'),(SELECT usersid FROM users WHERE username='user')),
    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist Brotherhood'),(SELECT usersid FROM users WHERE username='sdr')),
--    ((SELECT animeid FROM anime WHERE name='Haikyuu!'),(SELECT usersid FROM users WHERE username='user')),
--    ((SELECT animeid FROM anime WHERE name='Hunter X Hunter'),(SELECT usersid FROM users WHERE username='user')),
--    ((SELECT animeid FROM anime WHERE name='Black Clover'),(SELECT usersid FROM users WHERE username='user')),
    ((SELECT animeid FROM anime WHERE name='Jibaku Shonen Hanako-kun'),(SELECT usersid FROM users WHERE username='osj')),
    ((SELECT animeid FROM anime WHERE name='Cardcaptor Sakura'),(SELECT usersid FROM users WHERE username='osj'));

--  V4 -------------------------------------------------------------------------------------

INSERT INTO season(animeid, name, num) VALUES
    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist'), 'First Part', 1),
    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist'), 'Second Part', 2),
    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist'), 'Third Part', 3),
    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist'), 'Fourth Part', 4),

    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist Brotherhood'), 'First Part', 1),
    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist Brotherhood'), 'Second Part', 2),
    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist Brotherhood'), 'Third Part', 3),
    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist Brotherhood'), 'Fourth Part', 4),
    ((SELECT animeid FROM anime WHERE name='Fullmetal Alchemist Brotherhood'), 'Fifth Part', 5),

    ((SELECT animeid FROM anime WHERE name='Haikyuu!'), 'First season', 1),
    ((SELECT animeid FROM anime WHERE name='Haikyuu!'), 'Second season', 2),
    ((SELECT animeid FROM anime WHERE name='Haikyuu!'), 'Third Season', 3),
    ((SELECT animeid FROM anime WHERE name='Haikyuu!'), 'To the Top', 4),

    ((SELECT animeid FROM anime WHERE name='Jibaku Shonen Hanako-kun'), 'First season', 1),

    ((SELECT animeid FROM anime WHERE name='Cardcaptor Sakura'), 'First season', 1),
    ((SELECT animeid FROM anime WHERE name='Cardcaptor Sakura'), 'Second season', 2),
    ((SELECT animeid FROM anime WHERE name='Cardcaptor Sakura'), 'Third Season', 3)
;
