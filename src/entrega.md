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

```java
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
Obtiene la información de un anime en concreto en la base de datos en base a su ID.

```java
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

Si no encuentra el anime en base a la ID en la base de datos este salta con el siguiente error:
```java
{
    "message": "No s'ha trobat l'anime amd id 5d25a203-7a3c-448b-95b5-414caaa86a3a"
}
```

###  POST /animes/
Añade un anime a la base de datos mediante un documento JSON.
```java
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

Si no puede añadir el anime en la base de datos es porque ya existe uno con ese nombre, 
asi que salta error.
```java
{
    "message": "Ja existeix un anime amb el nom 'Fullmetal Alchemist'"
}
```

###  DELETE /animes/{id}
Elimina un anime de la base de datos en base a su ID.
```java
{
    "message": "S'ha eliminat l'anime amd id 'c944c3a1-e1f2-4ec8-8ec1-7cc8c3218d92'"
}
```

No encuentra el anime en base a la ID en la base de datos, asi que salta error.
```java
{
    "message": "No s'ha trobat l'anime amd id 'c944c3a1-e1f2-4ec8-8ec1-7cc8c3218d91'"
}
```
<br><br>



---
<a id='id2'></a>


##  Users

###  GET /users/
Obtiene una lista de todos los usuarios en la base de datos.
```java

```

###  POST /users/
Añade un usuario a la base de datos mediante un documento JSON.
```java

```

No puede añadir el usuario en la base de datos porque ya existe uno con ese nombre,
asi que salta error.
```java
{
    "message": ""
}
```

###  DELETE /users/
Elimina todos los usuarios de la base de datos
```java
{
    "message": "S'ha eliminat tots els users"
}
```

###  DELETE /users/{id}
Elimina un usuario de la base de datos en base a su ID.
```java

```

No encuentra el usuario en base a la ID en la base de datos, asi que salta error.
```java
{
    "message": ""
}
```
<br><br>


---

<a id='id3'></a>

## File

### GET /files/
Obtiene una lista de todos los archivos en la base de datos.
```java

```

### GET /files/{id}
Obtiene una lista de un archivo en concreto en la base de datos en base a su ID.
```java

```

No encuentra el archivo en base a la ID en la base de datos, asi que salta error.
```java
{
    "message": ""
}
```

### POST /files/
Añade un archivo a la base de datos mediante un parametro de JPA MultipartFile.
```java

```

### DELETE /files/
Elimina todos los archivos de la base de datos
```java
{
    "message": "S'ha eliminat tots els files"
}
```

### DELETE /files/{id}
Elimina un archivo de la base de datos en base a su ID.
```java

```

No encuentra el archivo en base a la ID en la base de datos, asi que salta error.
```java
{
    "message": ""
}
```
<br><br>


---

<a id='id4'></a>

## Author

### GET /authors/
Obtiene una lista de todos los autores en la base de datos.
```java

```

### GET /authors/{id}
Obtiene una lista de un autor en concreto en la base de datos en base a su ID.
```java

```

No encuentra el autor en base a la ID en la base de datos, asi que salta error.
```java
{
    "message": ""
}
```
<br><br>



---
<a id='id5'></a>

## Genres

### GET /genres/
Obtiene una lista de todos los generos en la base de datos.
```java

```

### GET /genres/{id}
Obtiene una lista de un generos en concreto en la base de datos en base a su ID.
```java

```

No encuentra el genero en base a la ID en la base de datos, asi que salta error.
```java
{
    "message": ""
}
```
<br><br>



---
<a id='id6'></a>

## Favorite

### GET /favorites/
Obtiene una lista de todos los animes favoritos de un usuario.
```java

```

No puede autentificar el usuario introducido
```java
{
    "message": "No estas autoritzat"
}
```

### POST /favorites/
Añade un anime a favoritos mediante un documento JSON.
```java

```

No puede añadir el anime a favoritos porque no existe uno con ese id,
asi que salta error.
```java
{
    "message": "Aquesta id no perteneix a cap anime existent"
}
```


No puede añadir el anime a favoritos porque ya existe uno con ese id,
asi que salta error.
```java
{
    "message": "Aquest anime ja esta en favorits"
}
```

No puede autentificar el usuario introducido
```java
{
    "message": "No estas autoritzat"
}
```

### DELETE /favorites/{id}
Elimina un anime a favoritos en base a su ID.
```java

```

No encuentra el anime en base a la ID en la base de datos, asi que salta error.
```java
{
    "message": "Aquesta id no perteneix a cap anime existent"
}
```


No encuentra el anime en base a la ID en favoritos, asi que salta error.
```java
{
    "message": "Aquest anime no esta en favorits"
}
```

No puede autentificar el usuario introducido
```java
{
    "message": "No estas autoritzat"
}
```
<br><br>



---
<a id='id7'></a>

## Season

### GET /seasons/
### GET /seasons/{id}
### POST /seasons/
### DELETE /seasons/{id}
<br><br>



---
<a id='id8'></a>

## Episode

### GET /episodes/
### GET /episodes/{id}
### POST /episodes/
### DELETE /episodes/{id}
<br><br>




[comment]: <> (<details>)

[comment]: <> (<summary>How do I dropdown?</summary>)

[comment]: <> (<br>)

[comment]: <> (This is how you dropdown.)

[comment]: <> (</details>)