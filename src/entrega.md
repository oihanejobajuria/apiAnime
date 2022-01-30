# Proyecto Api Anime

###  Indice
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
11. [Custom Lists](#id11)


---

La aplicación tiene los siguientes Endpoints:

---
<a id='id1'></a>


## Anime

###  GET /animes/
Obtiene una lista de todos los animes en la base de datos.
```java

```

###  GET /animes/{id}
Obtiene una lista de un anime en concreto en la base de datos en base a su ID.
```java

```

No encuentra el anime en base a la ID en la base de datos, asi que salta error.
```java
{
    "message": ""
}
```

###  POST /animes/
Añade un anime a la base de datos mediante un documento JSON.
```java

```

No puede añadir el anime en la base de datos porque ya existe uno con ese nombre, 
asi que salta error.
```java
{
    "message": ""
}
```

###  DELETE /animes/{id}
Elimina un anime de la base de datos en base a su ID.
```java

```

No encuentra el anime en base a la ID en la base de datos, asi que salta error.
```java
{
    "message": ""
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