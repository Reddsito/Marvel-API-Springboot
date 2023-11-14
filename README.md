# Documentación del proyecto Marvel API

## Descripción
Este proyecto consiste en el desarrollo de una API en Java utilizando el 
framework **Spring Boot**, que consume la **API de Marvel** para obtener y almacenar información 
relacionada con personajes y cómics. La aplicación incluirá un esquema de autenticación 
basado en **Spring Security** para identificar a los usuarios que realizan búsquedas de 
información de **Marvel**.

## Características
- - -
1. **Consumo de API de Marvel:**
    - La aplicación se conecta a la API de Marvel para obtener información sobre personajes, cómics, historietas y series.

2. **Persistencia en Base de Datos:**
    - Se implementa un esquema de base de datos, preferiblemente MySQL, para almacenar toda la información necesaria.

3. **Funcionalidades de la API:**
    - **Buscar Personajes por Nombre, Historietas y Serie:** Proporciona una funcionalidad de búsqueda de personajes de Marvel según su nombre, historietas o serie.
    - **Listado de Cómics por Personaje:** Ofrece la posibilidad de obtener el listado de cómics asociados a un personaje específico.
    - **Obtener Imagen y Descripción de un Personaje:** Permite acceder a la imagen y descripción detallada de un personaje específico.
    - **Listas de Cómics Completas:** Muestra listas completas de cómics disponibles.
    - **Comic Filtrado por ID:** Permite acceder a información detallada de un cómic específico mediante su identificador.
    - **Búsquedas Realizadas por Usuarios:** Presenta la información sobre las búsquedas relacionadas con historietas realizadas por cualquier usuario.
    - **Búsquedas de un Usuario Específico:** Muestra las búsquedas realizadas por un usuario en particular.

4. **Autenticación:**
    - Se implementará un esquema de autenticación utilizando **Spring Security** para identificar a los usuarios que realizan búsquedas de información de Marvel.

5. **Scripts de Base de Datos:**
    - Se proporcionarán scripts de base de datos en el README, incluyendo la data inicial necesaria para ejecutar las APIs por primera vez.


## Tecnologías Utilizadas

- Java
- Spring Boot
- Spring Security
- MySQL
- API de Marvel

## Configuración
```yml
integration:
  marvel:
    public-key: #CLAVE PÚBLICA DE MARVEL
    private-key: #CLAVE PRIVADA DE MARVEL
    base-path: #URL DE LA API DE MARVEL (http://gateway.marvel.com/v1/public)
```
Asegúrate de obtener estas claves de la API de Marvel [Click para obtener](https://developer.marvel.com)

Además se debe de configuar en el archivo application.yml las propiedades de la base de datos
```yml
spring:
  datasource:
    url: #jdbc:mysql://<TU HOST>:<TU PUERTO>/<TU BASE DE DATOS>?useSSL=false
    driverClassName: #com.mysql.jdbc.Driver
    username: # TU USERNAME
    password: # TU PASSWORD
```

Y que no se te olvide la clave para el token (JWT) y el tiempo de expiración del token
```yml
security:
   jwt:
      secret-key: # ASDOHASODHASODHASHD1023W12DHB102312NDOHUJASIODH12HEJHNADSLDJN123HJKLASDH
      expiration-minute: # 30
```

## Paquetes del proyecto
- - -
* `com.marvel.api.marvelchallenge.controllers`: Contiene las clases controladores para las solicitudes web.
* `com.marvel.api.marvelchallenge.exceptions`: Contiene las clases relacionas con el control de las excepciones.
* `com.marvel.api.marvelchallenge.filters`: Incluye los filtros para interceptar las solicitudes web.
* `com.marvel.api.marvelchallenge.interceptors`: Interceptores de las solicitudes web.
* `com.marvel.api.marvelchallenge.mappers`: Mapeadores para convertir entre entidades y DTO.
* `com.marvel.api.marvelchallenge.persistence.entity`: Define las entidades de la base de datos.
* `com.marvel.api.marvelchallenge.integration.marvel`: Gestiona la integración de la API de marvel.
* `com.marvel.api.marvelchallenge.persistence.dto`: Define los DTO de la aplicación.
* `com.marvel.api.marvelchallenge.persistence.repository`: Define los repositorios para acceder a la base de datos.
* `com.marvel.api.marvelchallenge.security`: Contiene la configuración de seguridad y autenticación.
* `com.marvel.api.marvelchallenge.services`: Define las interfaces e implementaciones de servicios.
* `com.marvel.api.marvelchallenge.util`: Ofrece utilidad genérica.
* `com.marvel.api.marvelchallenge.security.validator`: Contiene validadores de seguridad para rutas.

## Clases Principales
- - -

### `MarvelAPIConfig`
* Clase de configuración que se encarga de proporcionar la clave pública y privada de Marvel y gestionar la autenticación en las solicitudes a la API de Marvel.
### `ComicRepository`
* Repositorio que interactúa con la API de Marvel para obtener información sobre cómics. Proporciona métodos para buscar cómics relacionados con personajes y buscar cómics por ID.
### `CharacterRepository`
* Repositorio que se conecta a la API de Marvel para obtener información sobre personajes. Contiene métodos para buscar personajes por criterios y obtener información detallada de un personaje por ID.
###  `JwtService`
* Servicio para la generación y validación de tokens JWT. Utilizado para la autenticación en la aplicación.
### `WebSecurity y SecurityBeanInjecto`
* Clases encargadas de toda la configuración para utilizar WebSecurity de manera correcta, desde
la inyección de Beans de provedores y manager, hasta la configuración de los filtros.
### `ComicService y CharacterService`
* Interfaces de servicio que definen operaciones relacionadas con cómics y personajes. Sus implementaciones se encuentran en ComicServiceImpl y CharacterServiceImpl, respectivamente.
###  `Controladores`
* Los controladores, como ComicController, CharacterController, y AuthenticationController, gestionan las solicitudes HTTP y responden con datos a los clientes.
### `Filtros e Interceptores`
* Los filtros e interceptores, como JwtAuthenticationFilter y UserInteractionInterceptor, agregan funcionalidad de seguridad y registro a la aplicación.
### `UserInteractionLog y Repositorio`
* La entidad UserInteractionLog y su repositorio gestionan los registros de las interacciones de los usuarios con la aplicación.

## Scripts SQL
- - - 
El proyecto incluye la configuración necesaria para que las tablas se creen automaticamente al ejecutar el proyecto. 
Por defecto esta configuración se encuentra comentada pero basta con 
descomentar las siguientes propiedades para que se creen las tablas:
```yml
  jpa:
     hibernate:
       # ddl-auto: create-drop
```

Elegir el tipo de creación de estructura que desee.

También se puede ejecutar los scripts individualmente:

* **Creación de tablas**: [click](./src/main/resources/marvel_app.sql)
* **Creación de registro**: [click](./src/main/resources/data-mysql.sql)

## Spring Security: Usuarios y Roles
Los scripts incluyen la creación de dos roles y dos usuarios. 
Estos roles son "CUSTOMER" y "ADMIN" y los dos usuarios son
"Enrique" y "Jossd" con las contraseñas "contrasena123" y "contrasena456" respectivamente. 

Los permisos del role **"CUSTOMER"** son:
* character:read-all
* character:read-detail
* comic:read-all
* comic:read-by-id
* user-interaction:read-my-interactions
  
Los permisos del role **"ADMIN"** son:
* character:read-all
* character:read-detail
* comic:read-all
* comic:read-by-id
* user-interaction:read-my-interactions
* user-interaction:read-all
* user-interaction:read-by-username