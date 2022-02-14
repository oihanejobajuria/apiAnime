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
Elimina un anime de la base de datos con su ID.
```json
{
    "message": "S'ha eliminat l'anime amb id 'c944c3a1-e1f2-4ec8-8ec1-7cc8c3218d92'"
}
```

No encuentra el anime con la ID proporcionada en la base de datos, asi que salta error.
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
Obtiene una lista de todos los usuarios en la base de datos.
```json
{
    "usersid": "24802385-e035-4386-a9eb-6ba0b2b0bccb",
    "username": "osj"
}
```

###  POST /users/register
Añade un usuario a la base de datos mediante un documento JSON.
```json
{
    "username": "user5",
    "password": "pass"
}
```

Si no puede añadirse el usuario a la base de datos porque ya existe uno con ese nombre,
saltará el siguiente error.
```json
{
    "message": "Ja existeix un usuari amb el nom 'user5'"
}
```

###  DELETE /users/
Elimina todos los usuarios de la base de datos.
```json
{
    "message": "S'ha eliminat tots els usuaris"
}
``` 

###  DELETE /users/{id}
Elimina un usuario de la base de datos con su ID.
```json
{
    "message": "S'ha eliminat l'usuari amb id '1292c661-213f-4b66-ae9e-1e5d00e6e043'"
}
```

Si no encuentra el usuario con el ID en la base de datos, saltará este error:
```json
{
    "message": "No s'ha trobat el user amb id '1292c661-213f-4b66-ae9e-1e5d00e6e043'"
}
``` 
<br><br>


---

<a id='id3'></a>

## File

### GET /files/
Obtiene una lista de todos los archivos en la base de datos:
```json
{
    "fileid": "6d3a3779-6a26-4157-a77f-9f171d3fac60",
    "contenttype": "image/png"
}
``` 

### GET /files/{id}
Obtiene una lista de un archivo en concreto en la base de datos con su ID.
```json
    imagen pertinente
```

Si no encuentra el archivo con la ID proporcionada en la base de datos, saltará este error:
```json
{
    "message": "File not found"
}
```

### POST /files/
Añade un archivo a la base de datos mediante un parametro de JPA MultipartFile.
```json
{
    "fileid": "6d3a3779-6a26-4157-a77f-9f171d3fac60",
    "contenttype": "image/png"
}
```

### DELETE /files/
Elimina todos los archivos de la base de datos
```json
{
    "message": "S'ha eliminat tots els arxius"
}
```

### DELETE /files/{id}
Elimina un archivo de la base de datos con su ID.
```json
{
    "message": "S'ha eliminat l'arxiu amb id '6d3a3779-6a26-4157-a77f-9f171d3fac60'"
}
```

No encuentra el archivo con la ID dada en la base de datos, asi que salta error.
```json
{
    "message": "No s'ha trobat l'arxiu amb id '6d3a3779-6a26-4157-a77f-9f171d3fac60'"
}
```
<br><br>


---

<a id='id4'></a>

## Author

### GET /authors/
Obtiene una lista de todos los autores en la base de datos.
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
```

### GET /authors/{id}
Obtiene una lista de un autor en concreto en la base de datos en base a su ID.
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
```
<br><br>



---
<a id='id5'></a>

## Genres

### GET /genres/
Obtiene una lista de todos los generos en la base de datos.
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
```

### GET /genres/{id}
Obtiene una lista de un genero en concreto en la base de datos con la ID proporcionada.
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

No encuentra el genero con la ID en la base de datos, asi que salta error.
```json
{
    "message": "No s'ha trobat l'autor amb id 6755e2ff-9b08-4af3-a133-eebe9eddf751"
}
```
<br><br>



---
<a id='id6'></a>

## Favorite

### GET /users/favorites/
Obtiene una lista de todos los animes favoritos de un usuario.
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
```

### POST /users/favorites/
Añade un anime a favoritos mediante un documento JSON.
```json
{
    "animeid": "a4d0cd49-6f54-466b-a261-124d52c1cb84",
    "usersid": "0a181e3e-2d2a-487f-9910-c63ed8e78c4b"
}
```

No puede añadir el anime a favoritos porque no existe uno con ese id, asi que salta error.
```json
{
    "message": "Aquesta id no pertany a cap anime existent"
}
```


No puede añadir el anime a favoritos porque ya existe uno con ese id, asi que salta error.
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
```

### DELETE /users/favorites/{id}
Elimina un anime de favoritos en base a su ID.
```json
{
    "message": "S'ha eliminat del favorits l'anime amb id 'a4d0cd49-6f54-466b-a261-124d52c1cb84'"
}
```

No encuentra el anime con la ID proporcionada en la base de datos, asi que salta error.
```json
{
    "message": "Aquesta id no pertany a cap anime existent"
}
```


No encuentra el anime con la ID proporcionada en favoritos, asi que salta error.
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
```
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
Obtiene una lista con una temporada en concreto debido a una ID proporcionada.
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

No encuentra la temporada con la ID proporcionada en la base de datos, asi que salta error.
```json
{
    "message": "No s'ha trobat la temporada amb id ee6c6c15-7ddd-4ff6-b05b-eee6e9283eb4"
}
```

### POST /animes/seasons/
Añade una temporada a la base de datos mediante un documento JSON.
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

No puede añadir la temporada a la base de datos porque no existe un anime con ese id, asi que salta error.
```json
{
    "message": "No existeix un anime amb el id 'a4d0cd49-6f54-466b-a261-124d52c1cb83'"
}
```

No puede añadir la temporada a la base de datos porque ya existe una con ese nombre, asi que salta error.
```json
{
    "message": "Ja existeix una temporada amb el nom 'First Season'"
}
```

### DELETE /animes/seasons/{id}
Elimina una temporada de la base de datos con una ID dada.
```json
{
    "message": "S'ha eliminat la temporada amb id '42b14a17-a353-4e60-8d85-9329f083778f'"
}
```

No encuentra la temporada con la ID proporcionada en la base de datos, asi que salta error.
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
Obtiene una lista de todos los episodios que hay en la base de datos.
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
Obtiene una lista con un episodio en concreto debido a una ID proporcionada.
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

No encuentra el episodio con la ID proporcionada en la base de datos, asi que salta error.
```json
{
    "message": "No s'ha trobat l'episodi amb id ddbefaf5-28a1-4496-b6e5-5b769a21ff98"
}
```

### POST /animes/seasons/episodes/
Añade un episodio a la base de datos mediante un documento JSON.
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

No puede añadir el episodio a la base de datos porque no existe la temporada con ese id, asi que salta error.
```json
{
    "message": "No existeix una temporada amb el id 'ee6c6c15-7ddd-4ff6-b05b-eee6e9283eb4'"
}
```

No puede añadir el episodio a la base de datos porque ya existe uno con ese nombre, asi que salta error.
```json
{
    "message": "Ja existeix un episodi amb el nom 'Episodio 1'"
}
```

### DELETE /animes/seasons/episodes/{id}
Elimina un episodio de la base de datos con una ID dada.
```json
{
    "message": "S'ha eliminat l'episodi amb id '9f29c536-454a-4345-a3ba-e88eeffeb68c'"
}
```

No encuentra la temporada con la ID proporcionada en la base de datos, asi que salta error.
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
Obtiene una lista de todos los episodios vistos de un usuario.
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

No puede autentificar el usuario introducido
```json
{
    "message": "No estas autoritzat"
}
```

### POST /users/viewed/
Añade un episodio a visto mediante un documento JSON.
```json
{
    "message": "S'ha afegit a episodis vistos el episodi Aquel que desafía al Sol amb id ddbefaf5-28a1-4496-b6e5-5b769a21ff99"
}
```

No puede añadir el episodio a vistos porque no existe uno con ese id, asi que salta error.
```json
{
    "message": "Aquesta id no pertany a cap episodi existent"
}
```

No puede añadir el episodio a vistos porque ya existe uno con ese id, asi que salta error.
```json
{
    "message": "L'episodi Aquel que desafía al Sol ja esta vist"
}
```

No puede autentificar el usuario introducido
```json
{
    "message": "No estas autoritzat"
}
```

### DELETE /users/viewed/{id}
Elimina un episodio de vistos en base a su ID.
```json
{
    "message": "S'ha eliminat de episodis vistos el episodi Aquel que desafía al Sol amb id ddbefaf5-28a1-4496-b6e5-5b769a21ff99"
}
```

No encuentra el episodio con la ID proporcionada en la base de datos, asi que salta error.
```json
{
    "message": "Aquesta id no pertany a cap episodi existent"
}
```

No encuentra el anime con la ID proporcionada en vistos, asi que salta error.
```json
{
    "message": "Aquest episodi no esta vist"
}
```

No puede autentificar el usuario introducido
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
Obtiene una lista de todos los usuarios que siguen al usuario autorizado.
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

No puede autentificar el usuario introducido
```json
{
    "message": "No estas autoritzat"
}
```

### GET /users/followby/
Obtiene una lista de todos los usuarios que sigue el usuario autorizado.
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

No puede autentificar el usuario introducido
```json
{
    "message": "No estas autoritzat"
}
```

### POST /users/follow/
Añade un usuario a seguidos mediante un documento JSON.
```json
{
    "userbase": "0a181e3e-2d2a-487f-9910-c63ed8e78c4b",
    "followers_list": "1870cea1-7f33-436c-9ad2-356812bda3a1"
}
```

No puede añadir el usuario a seguidos porque no existe uno con ese id, asi que salta error.
```json
{
    "message": "Aquest username no pertany a cap usari existent"
}
```

No puede autentificar el usuario introducido
```json
{
    "message": "No estas autoritzat"
}
```

### DELETE /users/unfollow/{id}
Elimina un usuario de seguidos en base a su ID.
```json
{
    "message": "Has deixat de seguir a user2"
}
```

No encuentra el usario con la ID proporcionada en la base de datos, asi que salta error.
```json
{
    "message": "Aquest username no pertany a cap usari existent"
}
```

No encuentra el usario con la ID proporcionada en seguidos, asi que salta error.
```json
{
    "message": "No segueixes a user2"
}
```

No puede autentificar el usuario introducido
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
Obtiene una lista de todas las listas de un usuario.
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

No puede autentificar el usuario introducido
```json
{
    "message": "No estas autoritzat"
}
```

### GET /users/watchlists/{id}
Obtiene una lista con una lista en concreto debido a una ID proporcionada.
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

No encuentra la temporada con la ID proporcionada en la base de datos, asi que salta error.
```json
{
    "message": "No s'ha trobat la temporada amb id 1859f8f7-7a2b-4cdf-82da-2252ea58ea81"
}
```

No puede autentificar el usuario introducido
```json
{
    "message": "No estas autoritzat"
}
```

### POST /users/watchlists/
Añade una lista a la base de datos mediante un documento JSON.
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

No puede añadir la lista a la base de datos porque ya existe una con ese nombre, asi que salta error.
```json
{
    "message": "Aquesta watchlist ja existeix"
}
```

No puede autentificar el usuario introducido
```json
{
    "message": "No estas autoritzat"
}
```

### DELETE /users/watchlists/{id}
Elimina una lista de la base de datos con una ID dada.
```json
{
    "message": "Has eliminat correctament la watchlist Lista de prueba"
}
```

No encuentra la lista con la ID proporcionada en la base de datos, asi que salta error.
```json
{
    "message": "Aquesta watchlist no existeix"
}
```

No puede autentificar el usuario introducido
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
Obtiene una lista de todos los animes en listas de un usuario.
```json
{
    "watchlistid": "e30263f6-8338-4f92-8c17-df630bc1d078",
    "animeid": "fa9156b8-da24-43da-a348-e116c54abc49"
}

No puede autentificar el usuario introducido
```
```json
{
    "message": "No estas autoritzat"
}
```

### POST /users/watchlist_animes/
Añade un anime a una lista mediante un documento JSON.
```json
{
    "watchlistid": "3bef69e5-f9f6-4a62-af62-3ef16f3c0da0",
    "animeid": "179dbd3a-dca8-4547-9fc5-7f1b4c23d448"
}
```

No puede añadir el anime a una lista que no es tuya, asi que salta error.
```json
{
    "message": "No puedes editar watchlists que no son tuyas"
}
```

No puede añadir el anime a una lista porque no existe esta, asi que salta error.
```json
{
    "message": "Aquesta watchlist no existeix"
}
```

No puede añadir el anime a una lista porque no existe uno con ese id, asi que salta error.
```json
{
    "message": "Aquest anime no existeix"
}
```

No puede añadir el anime a una lista porque ya existe uno con ese id, asi que salta error.
```json
{
    "message": "Ja existeix el anime a la watchlist"
}
```

No puede autentificar el usuario introducido
```json
{
    "message": "No estas autoritzat"
}
```

### DELETE /users/watchlist_animes/
Elimina un anime de una lista en base a su ID.
```json
{
    "message": "S'ha eliminat l'anime Fullmetal Alchemist de la watchlist De la infacia"
}
```

No puede añadir el anime a una lista que no es tuya, asi que salta error.
```json
{
    "message": "No puedes editar watchlists que no son tuyas"
}
```

No puede añadir el anime a una lista porque no existe esta, asi que salta error.
```json
{
    "message": "Aquesta watchlist no existeix"
}
```

No puede añadir el anime a una lista porque existe uno con esa ID, asi que salta error.
```json
{
    "message": "Aquest anime no existeix"
}
```

No encuentra el anime con la ID proporcionada en la lista, asi que salta error.
```json
{
    "message": "No existeix el anime a la watchlist"
}
```

No puede autentificar el usuario introducido
```json
{
    "message": "No estas autoritzat"
}
```

<br><br>


