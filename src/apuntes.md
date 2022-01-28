#Apuntes API

###Aclaraciones

Usaremos de ejemplo una api de peliculas e intentaremos poner todos
los ejemplos relacionados con ella, sobretodo con la base de _pelicula_.
P.e., los java seran _Pelicula.java_ y las tablas _pelicula_

---

##Rutas
Los puntos separan los ___Package___ de manera abreviada, cada uno
de ellos es un directorio.

- ___src.main.java.com.example.peli___ <br>
  donde estan guardados los java (en los apuntes se parte de esta
  base y se le llamará así para abreviar).


- ___src.main.java.com.example.peli.controller___ <br>
  es la clase java dónde se harán los GetMapping, PostMapping y
  DeleteMapping, habrá siempre una por cada mapeado.


- ___src.main.java.com.example.peli.domain.dto___ <br>
  es la clase java dónde se harán clases que no son 100% relacionadas
  con las tablas pero si son necesarias para darnos más facilidad a
  la hora de estar trabajando en los _Controller_ como un mensaje de
  error pre hecho.


- ___src.main.java.com.example.peli.domain.model___ <br>
  en esta raiz se  crean las clases javas que estarán relacionadas
  con el sql.


- ___src.main.java.com.example.peli.domain.model.compositekeys___ <br>
  son las clases java dónde se crearan claves para hacernos más fácil
  trabajar con tablas con clave doble.


- ___src.main.java.com.example.peli.domain.model.projection___ <br>
  son las clases java que nos serviran para ensenyar de forma reducida
  la informacion que queramos de las clases que estan en el model


- ___src.main.java.com.example.peli.repository___ <br>
  es la clase java que relaciona los domain con los sql guardados y
  contiene metodos guardados para aceder a datos de la tabla (tambien
  pueden ser creados)


- ___src.main.resources___ <br>
  donde estan las propiedades de conexion al "server"


- ___src.main.resources.db.migration___ <br>
  donde estan guardas los sql

---

##Controller

---

##Domain

###Domain
Son clases, estas sirven para relacionar el sql con los javas. Para
conseguirlo has de poner dos etiquetas para sentenciar la clase, estas
son:

```java
    @Entity
    @Table(name = "pelicula")
```

Dentro de esta clase siempre estaran los getters y setters de la clase y
la instanciacion en las variables de la version de la tabla en java:

```java
    public String name; (java)  ->   name text (sql)
    public int year; (java)     ->   year int (sql)
```

Esas son las partes basicas y esenciales para un buen Domain. Pero hay casos
en los que se han de relacionar con otras tablas, pe pelicula y actores.
En ese caso

###Compositekeys

###Projection

---

##Repository
Es una interface, extiende de JpaRepository. Se guia por dos claves:
la clase a la que se referira el repositorio (es la del domain.model),
y su clave principal (normalmente UUID).

```java
    public interface PeliculaRepository extends JpaRepository<Pelicula, UUID>{}
```

Hay unos metodos prehechos como findby(), findbyAll(), etc. Estos
son querys de sql para sirven para obtener informacion de la clase
pertinente, la que se indica cuando se crea el repositorio.

Estos se pueden sobreescribir, p.e.:

```java
    List<ProjectionPeliculaSoloNombre> findby();
```

De la manera anterior ese metodo retornara una lista de tipo ProjectionX.
Si queremos hacerla universal para varias projeciones se hace de la
siguiente manera:

```java
    <T> List<T> findby();
```

Asimismo podemos crear metodos nuevos. Solo necesitamos saber que query
realizar y que retornara. P.e.:

```java
    @Query("select id from Pelicula")
    List<String> getPeliculaIds();
```

Obviamente estos metodos puedes crearlos y necesitar enviar parametros.
P.e.:

```java
    <T> T findByGenreid(UUID genreid, Class<T> type);
    <T> List<T> findByName(String name, Class<T> type);
```


---

##Resources

###Resources

###bd.migration