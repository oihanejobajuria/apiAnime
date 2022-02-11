# Proyecto Api Anime

###  Índice
1. [Anime](#id1)
2. [Users](#id2)
3. [File](#id3)
4. [Author](#id4)
5. [Genres](#id5)
6. [Favorite](#id6)
7. [Season](#id7)
8. [Episode](#id8)
9. [Viewed](#id9)
10. [Follow](#id10)
11. [Create custom lists](#id11)
12. [Add to custom lists](#id12)


---

La aplicación tiene los siguientes Endpoints:

---
<a id='id1'></a>


## Anime

###  GET /animes/
Obtiene una lista de todos los animes que hay en la base de datos.

```json
{
    "animeid": "5d25a203-7a3c-448b-95b5-414caaa86a3e",
    "name": "Fullmetal Alchemist",
    "genres": [
        {
            "label": "action"
        }
    ]
}
```

###  GET /animes/{id}
Obtiene la información de un anime en concreto en la base de datos con su ID.

```json
{
    "animeid": "5d25a203-7a3c-448b-95b5-414caaa86a3e",
    "name": "Fullmetal Alchemist",
    "description": "FMA",
    "type": "action",
    "year": 2003,
    "imageurl": "/images/123",
    "authors": [
        {
            "name": "Hiromu Arakawa"
        }
    ],
    "genres": [
        {
            "label": "action"
        }
    ],
    "seasons": [
        {
            "name": "First Part",
            "episodes": [
                {
                    "name": "Aquel que desafía al Sol"
                },
                {
                    "name": "El cuerpo del condenado"
                }
            ]
        }
    ]
}
```

Si no encuentra el anime en base a la ID en la base de datos nos saldrá el siguiente error:
```json
{
    "message": "No s'ha trobat l'anime amb id 5d25a203-7a3c-448b-95b5-414caaa86a3a"
}
```

###  POST /animes/
Añade un anime a la base de datos mediante un documento JSON.
```json
{
    "animeid": "c944c3a1-e1f2-4ec8-8ec1-7cc8c3218d92",
    "name": "Anime de prueba",
    "description": "Este anime es de prueba",
    "type": "e30263f6",
    "year": 2022,
    "imageurl": "img/1.png",
    "authors": null,
    "genres": null,
    "favoritedby": null,
    "watchlistedIn": null,
    "seasons": null
}
```

Si no puede añadir el anime en la base de datos es porque ya existe uno con ese nombre, así que saltará este error.
```json
{
    "message": "Ja existeix un anime amb el nom 'Fullmetal Alchemist'"
}
```

###  DELETE /animes/{id}
Elimina un anime de la base de datos en base a su ID.
```json
{
    "message": "S'ha eliminat l'anime amb id 'c944c3a1-e1f2-4ec8-8ec1-7cc8c3218d92'"
}
```

No encuentra el anime en base a la ID en la base de datos, asi que salta error.
```json
{
    "message": "No s'ha trobat l'anime amb id 'c944c3a1-e1f2-4ec8-8ec1-7cc8c3218d91'"
}
```
<br><br>



---
<a id='id2'></a>


##  Users

###  GET /users/
<!-- Obtiene una lista de todos los usuarios en la base de datos.
```json
{
    "usersid": "24802385-e035-4386-a9eb-6ba0b2b0bccb",
    "username": "osj"
}
``` -->

###  POST /users/register
<!-- Añade un usuario a la base de datos mediante un documento JSON.
```json
{
    "username": "user5",
    "password": "pass"
}
```

No puede añadir el usuario en la base de datos porque ya existe uno con ese nombre,
asi que salta error.
```json
{
    "message": "Ja existeix un usuari amb el nom 'user5'"
}
``` -->

###  DELETE /users/
<!-- Elimina todos los usuarios de la base de datos
```json
{
    "message": "S'ha eliminat tots els users"
}
``` -->

###  DELETE /users/{id}
<!-- Elimina un usuario de la base de datos en base a su ID.
```json
{
    "message": "S'ha eliminat l'usuari amb id '1292c661-213f-4b66-ae9e-1e5d00e6e043'"
}
```

No encuentra el usuario en base a la ID en la base de datos, asi que salta error.
```json
{
    "message": "No s'ha trobat el user amb id '1292c661-213f-4b66-ae9e-1e5d00e6e043'"
}
``` -->
<br><br>


---

<a id='id3'></a>

## File

### GET /files/
<!-- Obtiene una lista de todos los archivos en la base de datos.
```json
{
    "fileid": "6d3a3779-6a26-4157-a77f-9f171d3fac60",
    "contenttype": "image/png"
}
``` -->

### GET /files/{id}
<!-- Obtiene una lista de un archivo en concreto en la base de datos en base a su ID.
```json
    imagen pertinente
```

No encuentra el archivo en base a la ID en la base de datos, asi que salta error.
```json
{
    "message": "File not found"
}
``` -->

### POST /files/
<!-- Añade un archivo a la base de datos mediante un parametro de JPA MultipartFile.
```json
{
    "fileid": "6d3a3779-6a26-4157-a77f-9f171d3fac60",
    "contenttype": "image/png"
}
``` -->

### DELETE /files/
<!-- Elimina todos los archivos de la base de datos
```json
{
    "message": "S'ha eliminat tots els files"
}
``` -->

### DELETE /files/{id}
<!-- Elimina un archivo de la base de datos en base a su ID.
```json
{
    "message": "S'ha eliminat el files amb id '6d3a3779-6a26-4157-a77f-9f171d3fac60'"
}
```

No encuentra el archivo en base a la ID en la base de datos, asi que salta error.
```json
{
    "message": "No s'ha trobat el files amb id '6d3a3779-6a26-4157-a77f-9f171d3fac60'"
}
``` -->
<br><br>


---

<a id='id4'></a>

## Author

### GET /authors/
<!-- Obtiene una lista de todos los autores en la base de datos.
```json
{
    "authorid": "570eae56-c135-4b0c-8089-4dbeab154c0f",
    "name": "Hiromu Arakawa",
    "animes": [
        {
            "animeid": "4ba65eb3-be30-48e0-8007-aa9175fbb573",
            "name": "Fullmetal Alchemist"
        },
        {
            "animeid": "7f2c35a4-3ec5-4db5-ba33-c3e7fd2ee1f8",
            "name": "Fullmetal Alchemist Brotherhood"
        }
    ]
}
``` -->

### GET /authors/{id}
<!-- Obtiene una lista de un autor en concreto en la base de datos en base a su ID.
```json
{
    "authorid": "b471feda-23d7-44be-92ec-bef7a52be99c",
    "name": "Haruichi Furudate",
    "animes": [
        {
            "animeid": "9c0518be-f893-45f8-a828-1cf97d2bf225",
            "name": "Haikyuu!"
        }
    ]
}
```

No encuentra el autor en base a la ID en la base de datos, asi que salta error.
```json
{
    "message": "No s'ha trobat l'autor amb id b471feda-23d7-44be-92ec-bef7a52be99a"
}
``` -->
<br><br>



---
<a id='id5'></a>

## Genres

### GET /genres/
<!-- Obtiene una lista de todos los generos en la base de datos.
```json
{
    "genreid": "6755e2ff-9b08-4af3-a133-eebe9eddf752",
    "label": "sobrenatural",
    "animes": [
        {
            "animeid": "311cb554-f691-4416-a9ab-75df9b270c36",
            "name": "Jibaku Shonen Hanako-kun"
        }
    ]
}
``` -->

### GET /genres/{id}
<!-- Obtiene una lista de un generos en concreto en la base de datos en base a su ID.
```json
{
    "genreid": "6755e2ff-9b08-4af3-a133-eebe9eddf752",
    "label": "sobrenatural",
    "imageurl": "img/1",
    "animes": [
        {
            "animeid": "311cb554-f691-4416-a9ab-75df9b270c36",
            "name": "Jibaku Shonen Hanako-kun"
        }
    ]
}
```

No encuentra el genero en base a la ID en la base de datos, asi que salta error.
```json
{
    "message": "No s'ha trobat l'autor amb id 6755e2ff-9b08-4af3-a133-eebe9eddf751"
}
``` -->
<br><br>



---
<a id='id6'></a>

## Favorite

### GET /users/favorites/
<!-- Obtiene una lista de todos los animes favoritos de un usuario.
```json
{
    "animeid": "7f2c35a4-3ec5-4db5-ba33-c3e7fd2ee1f8",
    "name": "Fullmetal Alchemist Brotherhood"
}
```

No puede autentificar el usuario introducido
```json
{
    "message": "No estas autoritzat"
}
``` -->

### POST /users/favorites/
<!-- Añade un anime a favoritos mediante un documento JSON.
```json
{
    "animeid": "a4d0cd49-6f54-466b-a261-124d52c1cb84",
    "usersid": "0a181e3e-2d2a-487f-9910-c63ed8e78c4b"
}
```

No puede añadir el anime a favoritos porque no existe uno con ese id,
asi que salta error.
```json
{
    "message": "Aquesta id no pertany a cap anime existent"
}
```


No puede añadir el anime a favoritos porque ya existe uno con ese id,
asi que salta error.
```json
{
    "message": "Aquest anime ja esta en favorits"
}
```

No puede autentificar el usuario introducido
```json
{
    "message": "No estas autoritzat"
}
``` -->

### DELETE /users/favorites/{id}
<!-- Elimina un anime a favoritos en base a su ID.
```json
{
    "message": "S'ha eliminat del favorits l'anime amb id 'a4d0cd49-6f54-466b-a261-124d52c1cb84'"
}
```

No encuentra el anime en base a la ID en la base de datos, asi que salta error.
```json
{
    "message": "Aquesta id no pertany a cap anime existent"
}
```


No encuentra el anime en base a la ID en favoritos, asi que salta error.
```json
{
    "message": "Aquest anime no esta en favorits"
}
```

No puede autentificar el usuario introducido
```json
{
    "message": "No estas autoritzat"
}
``` -->
<br><br>



---
<a id='id7'></a>

## Season

### GET /animes/seasons/
Obtiene una lista de todos las temporadas que hay en la base de datos.
```json
{
    "seasonid": "ee6c6c15-7ddd-4ff6-b05b-eee6e9283eb5",
    "name": "First Part",
    "num": 1,
    "animeWithSeasons": [
        {
            "animeid": "179dbd3a-dca8-4547-9fc5-7f1b4c23d448",
            "name": "Fullmetal Alchemist"
        }
    ]
}
```

### GET /animes/seasons/{id}
```json
{
    "seasonid": "ee6c6c15-7ddd-4ff6-b05b-eee6e9283eb5",
    "name": "First Part",
    "num": 1,
    "animeWithSeasons": [
        {
            "animeid": "179dbd3a-dca8-4547-9fc5-7f1b4c23d448",
            "name": "Fullmetal Alchemist",
            "description": "FMA",
            "type": "action",
            "year": 2003
        }
    ]
}
```
```json
{
    "message": "No s'ha trobat la temporada amb id ee6c6c15-7ddd-4ff6-b05b-eee6e9283eb4"
}
```

### POST /animes/seasons/
```json
{
    "seasonid": "42b14a17-a353-4e60-8d85-9329f083778f",
    "name": "First Season",
    "num": 1,
    "animeWithSeasons": [
        {
            "animeid": "a4d0cd49-6f54-466b-a261-124d52c1cb84",
            "name": "Hunter X Hunter",
            "description": "HXH",
            "type": "action",
            "year": 2011
        }
    ]
}
```
```json
{
    "message": "No existeix un anime amb el id 'a4d0cd49-6f54-466b-a261-124d52c1cb83'"
}
```
```json
{
    "message": "Ja existeix una temporada amb el nom 'First Season'"
}
```

### DELETE /animes/seasons/{id}
```json
{
    "message": "S'ha eliminat la temporada amb id '42b14a17-a353-4e60-8d85-9329f083778f'"
}
```
```json
{
    "message": "No s'ha trobat la temporada amb l'id '42b14a17-a353-4e60-8d85-9329f083778f'"
}
```

<br><br>



---
<a id='id8'></a>

## Episode

### GET /animes/seasons/episodes/
```json
{
    "episodeid": "ddbefaf5-28a1-4496-b6e5-5b769a21ff99",
    "name": "Aquel que desafía al Sol",
    "num": 1,
    "seasonWithEpisodes": [
        {
            "seasonid": "ee6c6c15-7ddd-4ff6-b05b-eee6e9283eb5",
            "name": "First Part",
            "num": 1,
            "animeWithSeasons": [
                {
                    "animeid": "179dbd3a-dca8-4547-9fc5-7f1b4c23d448",
                    "name": "Fullmetal Alchemist"
                }
            ]
        }
    ]
}
```

### GET /animes/seasons/episodes/{id}
```json
{
    "episodeid": "ddbefaf5-28a1-4496-b6e5-5b769a21ff99",
    "name": "Aquel que desafía al Sol",
    "num": 1,
    "seasonWithEpisodes": [
        {
            "seasonid": "ee6c6c15-7ddd-4ff6-b05b-eee6e9283eb5",
            "name": "First Part",
            "num": 1,
            "animeWithSeasons": [
                {
                    "animeid": "179dbd3a-dca8-4547-9fc5-7f1b4c23d448",
                    "name": "Fullmetal Alchemist",
                    "description": "FMA",
                    "type": "action",
                    "year": 2003
                }
            ]
        }
    ]
}
```
```json
{
    "message": "No s'ha trobat l'episodi amb id ddbefaf5-28a1-4496-b6e5-5b769a21ff98"
}
```

### POST /animes/seasons/episodes/
```json
{
    "episodeid": "9f29c536-454a-4345-a3ba-e88eeffeb68c",
    "name": "Episodio 1",
    "num": 15,
    "seasonWithEpisodes": [
        {
            "seasonid": "ee6c6c15-7ddd-4ff6-b05b-eee6e9283eb5",
            "name": "First Part",
            "num": 1,
            "animeWithSeasons": [
                {
                    "animeid": "179dbd3a-dca8-4547-9fc5-7f1b4c23d448",
                    "name": "Fullmetal Alchemist",
                    "description": "FMA",
                    "type": "action",
                    "year": 2003
                }
            ]
        }
    ]
}
```
```json
{
    "message": "Ja existeix un episodi amb el nom 'Episodio 1'"
}
```
```json
{
    "message": "No existeix una temporada amb el id 'ee6c6c15-7ddd-4ff6-b05b-eee6e9283eb4'"
}
```

### DELETE /animes/seasons/episodes/{id}
```json
{
    "message": "S'ha eliminat l'episodi amb id '9f29c536-454a-4345-a3ba-e88eeffeb68c'"
}
```
```json
{
    "message": "No s'ha trobat l'episodi amb l'id '9f29c536-454a-4345-a3ba-e88eeffeb68c'"
}
```

<br><br>



---
<a id='id9'></a>

## Viewed

### GET /users/viewed/
```json
{
    "episodeid": "e4d144b3-23c2-4d15-ae77-f81f3c4491d9",
    "name": "El aliado más fuerte",
    "num": 3,
    "seasonWithEpisodes": [
        {
            "seasonid": "107f269e-98f0-4ed5-a480-d9586ecea77e",
            "name": "First season",
            "num": 1,
            "animeWithSeasons": [
                {
                    "animeid": "6e94df92-8697-41d8-809d-4fc5df9ab26c",
                    "name": "Haikyuu!"
                }
            ]
        }
    ]
}
```
```json
{
    "message": "No estas autoritzat"
}
```

### POST /users/viewed/
```json
{
    "message": "S'ha afegit a episodis vistos el episodi Aquel que desafía al Sol amb id ddbefaf5-28a1-4496-b6e5-5b769a21ff99"
}
```
```json
{
    "message": "L'episodi Aquel que desafía al Sol ja esta vist"
}
```
```json
{
    "message": "Aquesta id no pertany a cap episodi existent"
}
```
```json
{
    "message": "No estas autoritzat"
}
```

### DELETE /users/viewed/{id}
```json
{
    "message": "S'ha eliminat de episodis vistos el episodi Aquel que desafía al Sol amb id ddbefaf5-28a1-4496-b6e5-5b769a21ff99"
}
```
```json
{
    "message": "Aquesta id no pertany a cap episodi existent"
}
```
```json
{
    "message": "Aquest episodi no esta vist"
}
```
```json
{
    "message": "No estas autoritzat"
}
```

<br><br>



---
<a id='id10'></a>

## Follow

### GET /users/follow/
```json
{
    "follow": [
        {
            "usersid": "cc841605-89ca-46cf-98ca-24a1294b2718",
            "username": "osj"
        }
    ]
}
```
```json
{
    "message": "No estas autoritzat"
}
```

### GET /users/followby/
```json
{
    "followBy": [
        {
            "usersid": "d697cedc-e73a-42c1-868f-f5a1d845c064",
            "username": "user3"
        }
    ]
}
```
```json
{
    "message": "No estas autoritzat"
}
```

### POST /users/follow/
```json
{
    "userbase": "0a181e3e-2d2a-487f-9910-c63ed8e78c4b",
    "followers_list": "1870cea1-7f33-436c-9ad2-356812bda3a1"
}
```
```json
{
    "message": "Aquest username no pertany a cap usari existent"
}
```
```json
{
    "message": "No estas autoritzat"
}
```

### DELETE /users/unfollow/{id}
```json
{
    "message": "Has deixat de seguir a user2"
}
```
```json
{
    "message": "Aquest username no pertany a cap usari existent"
}
```
```json
{
    "message": "No segueixes a user2"
}
```
```json
{
    "message": "No estas autoritzat"
}
```

<br><br>



---
<a id='id11'></a>

## Create custom list

### GET /users/watchlists/
```json
{
    "watchlistid": "ea2a0669-47ef-43f5-a7fe-8d2c18aa68f4",
    "name": "Animes de la mama",
    "userWithList": [
        {
            "username": "sdr"
        }
    ],
    "animesInWatchlist": []
}
```
```json
{
    "message": "No estas autoritzat"
}
```

### GET /users/watchlists/{id}
```json
{
    "watchlistid": "1859f8f7-7a2b-4cdf-82da-2252ea58ea80",
    "name": "Tienen remake",
    "userWithList": [
        {
            "username": "user"
        }
    ],
    "animesInWatchlist": [
        {
            "animeid": "a4d0cd49-6f54-466b-a261-124d52c1cb84",
            "name": "Hunter X Hunter"
        }
    ]
}
```
```json
{
    "name": "De la infacia",
    "description": "Estos animes los veia de pequenya",
    "userWithList": [
        {
            "username": "user"
        }
    ],
    "animesInWatchlist": [
        {
            "animeid": "5630a479-4ad8-446c-ac6c-7898c06b3a39",
            "name": "Cardcaptor Sakura"
        }
    ]
}
```
```json
{
    "message": "No estas autoritzat"
}
```

### POST /users/watchlists/
```json
{
    "name": "Lista de prueba",
    "description": "lorem patata",
    "userWithList": [
        {
            "username": "user"
        }
    ],
    "animesInWatchlist": null
}
```
```json
{
    "message": "Aquesta watchlist ja existeix"
}
```
```json
{
    "message": "No estas autoritzat"
}
```

### DELETE /users/watchlists/{id}
```json
{
    "message": "Has eliminat correctament la watchlist Lista de prueba"
}
```
```json
{
    "message": "Aquesta watchlist no existeix"
}
```
```json
{
    "message": "No estas autoritzat"
}
```

<br><br>



---
<a id='id12'></a>

## Add to custom list

### GET /users/watchlist_animes/
```json
{
    "watchlistid": "e30263f6-8338-4f92-8c17-df630bc1d078",
    "animeid": "fa9156b8-da24-43da-a348-e116c54abc49"
}
```
```json
{
    "message": "No estas autoritzat"
}
```

### POST /users/watchlist_animes/
```json
{
    "watchlistid": "3bef69e5-f9f6-4a62-af62-3ef16f3c0da0",
    "animeid": "179dbd3a-dca8-4547-9fc5-7f1b4c23d448"
}
```
```json
{
    "message": "No puedes editar watchlists que no son tuyas"
}
```
```json
{
    "message": "Aquest anime no existeix"
}
```
```json
{
    "message": "Ja existeix el anime a la watchlist"
}
```
```json
{
    "message": "No existeix el anime a la watchlist"
}
```
```json
{
    "message": "No estas autoritzat"
}
```

### DELETE /users/watchlist_animes/
```json
{
    "message": "S'ha eliminat l'anime Fullmetal Alchemist de la watchlist De la infacia"
}
```
```json
{
    "message": "No existeix el anime a la watchlist"
}
```
```json
{
    "message": "Aquest anime no existeix"
}
```
```json
{
    "message": "Aquesta watchlist no existeix"
}

```
```json
{
    "message": "No puedes editar watchlists que no son tuyas"
}
```
```json
{
    "message": "No estas autoritzat"
}
```

<br><br>


