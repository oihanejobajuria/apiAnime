# Apuntes API

### Índice
1. [Rutas](#id1)
2. [Controller](#id2)
3. [Domain](#id3)
   1. [Model](#id31)
   2. [Model.Compositekeys](#id32)
   3. [Model.Projection](#id33)
   4. [Dto](#id34)
4. [Repository](#id4)
5. [Resources](#id5)
   1. [Resources](#id51)
   2. [db.migration](#id52)

---

### Aclaraciones

Usaremos de ejemplo una api de peliculas e intentaremos poner todos
los ejemplos relacionados con ella, sobretodo con la base de _pelicula_.
P.e., los java seran _Pelicula.java_ y las tablas _pelicula_

---

<a id='id1'></a>

## Rutas

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

<a id='id2'></a>

## Controller

---

<a id='id3'></a>

## Domain

<a id='id31'></a>

### Model
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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID peliculaid;     ->   animeid UUID not null default gen_random_uuid() (sql)

    public String name; (java)  ->   name text (sql)
    public int year; (java)     ->   year int (sql)

```

Esas son las partes basicas y esenciales para un buen Domain. Pero hay casos
en los que se han de relacionar con otras tablas, p.e. pelicula y actores.
En ese caso ponemos la relación entre las tablas y con que clave se relacion:

Ejemplo de relación ManyToMany:
```java

    @ManyToMany
    @JoinTable(name = "pelicula_actor",
            joinColumns = @JoinColumn(name = "peliculaid"),
            inverseJoinColumns = @JoinColumn(name = "actorid"))
    @JsonIgnoreProperties("peliculas")
    public Set<Actor> actores;

    
    @ManyToMany(mappedBy = "actor")
    @JsonIgnoreProperties("actores")
    public Set<Pelicula> peliculas;

```

Ejemplo de relación ManyToMany:
```java
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peliculaWithPersonaje")
    public List<Personaje> personajes;


    @ManyToOne
    @JoinColumn(
            name="peliculaid", nullable = false, updatable = false)
    @JsonIgnoreProperties("personajes")
    public Pelicula peliculaWithPersonaje;
```

Aquí estan los ejemplos de que [tipo de relaciones](https://www.tutorialspoint.com/es/jpa/jpa_entity_relationships.htm)
existen y como se relacionan entre ellas.

<a id='id32'></a>
###
Model. Compositekeys

Son clases y sivren para las tablas de sql que tienen dos claves 
primarias. Aqui se implementa la clase Serializable y se ponen 
las instanciaciones de las claves, p.e.:

```java
    public class ClavePeliculaId_ActorId implements Serializable {
        public UUID peliculaid;
        public UUID actorid;
    }
```

Luego en el java que esta en el domain, a parte de ponerle las 
dos etiquetas pertinentes que pone arriba se ha de poner la 
referencia de la Compositekey. Solo en aquella en la que no se hace 
el JoinTable.

```java
    @Entity
    @Table(name = "pelicula")
    @IdClass(ClavePeliculaId_ActorId.class)
```

<a id='id33'></a>
###
Model. Projection

Son interface, sirven para enseñar datos concretos especificados de una 
clase del Domain, pueden utilizarse para crear metodos en el Repository
o para ser llamados en el Controller.

En él se llaman a si mismo en la clase del Domain, se utilizaria lo que 
es el getter del tipo de dato que queremos obtener.

```java
    public interface ProjectionPelicula_idName {
        UUID getPeliculaid();
        String getName();
    }
```

Este puede crear Set de otras proyecciones para enseñar datos de 
relacionanes que tienen en el Domain con ciertos datos en concreto. 
A la hora de hacer esto hay que tener en cuenta que al estar 
relacionados hay que ignorar el campo del Domain de la tabla a la que 
estamos tratando. P.e.:

Si en el Domain de _Pelicula.java_ hay un campo ManyToMany a _Actor.java_ 
-> actors, y viceversa en _Actor.java_ hay un campo ManyToMany a 
_Pelicula.java_ -> peliculas. Esto sería lo que habria que poner en la
proyección de cada uno.

```java
    public interface ProjectionPelicula_idName {
        UUID getPeliculaid();
        String getName();
        
       @JsonIgnoreProperties("actors")
        Set<ProjectionActor_idName> getActor();
    }
```

```java
    public interface ProjectionPelicula_idName {
        UUID getActorid();
        String getName();

        @JsonIgnoreProperties("peliculas")
        Set<ProjectionPelicula_idName> getPeliculas();
    }
```

Luego habrá que editar el Repository para crear un metodo que al pasarle
un parametro o ninguno, como se prefiera, devuelva la proyeccion en vez 
de el archivo del Domain. *1

<a id='id34'></a>

## Dto

---

<a id='id4'></a>

### Repository
Es una interface, extiende de JpaRepository. Se guia por dos claves:
la clase a la que se referira el repositorio (es la del domain.model),
y su clave principal (normalmente UUID).

```java
    public interface PeliculaRepository extends JpaRepository<Pelicula, UUID>{}
```

Hay unos metodos prehechos como findby(), findbyAll(), etc. Estos
son querys de sql para sirven para obtener informacion de la clase
pertinente, la que se indica cuando se crea el repositorio.

Estos se pueden sobreescribir, p.e. *1:

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

<a id='id5'></a>

## Resources

<a id='id51'></a>

### Resources

<a id='id52'></a>

### bd.migration