# Digital Store - API Backend

## Descripción

Este proyecto implementa el backend para **Digital Store**, una tienda en línea propuesta en el curso de front-end. La API REST proporcionará los servicios necesarios para la gestión de productos, usuarios y autenticación, permitiendo la interacción con la base de datos y garantizando seguridad mediante JWT.

## Funcionalidades

La API permitirá gestionar los datos y la lógica de negocio de la tienda digital con las siguientes funcionalidades:

- **Gestión de productos**: CRUD de productos.
- **Gestión de usuarios**: Registro, edición y eliminación de usuarios.
- **Autenticación y autorización**: Implementación de seguridad con JWT.
- **Gestión de categorías**: Organización de productos por categorías.

## Tecnologías Utilizadas

- **Java 11** (o superior)
- **Spring Boot 3**
- **Spring Data JPA** (para manejo de base de datos)
- **Spring Security** (para autenticación y autorización)
- **JWT (JSON Web Tokens)**
- **MySQL 8.0** (o superior)
- **Maven** (gestor de dependencias)
- **Docker** (para despliegue en contenedores)
- **Postman** (para pruebas de API)
- **DBeaver** (para administración de base de datos)

## Instalación

### 1. Clonar el repositorio

```bash
git clone https://github.com/FranciscoMendiola/dw-backend
cd dw-backend
```

### 2. Configurar la base de datos

Asegúrate de tener MySQL instalado y ejecutando. Luego, crea una base de datos para la aplicación:

```sql
CREATE DATABASE digital_store;
```

### 3. Configurar las variables de entorno

Edita el archivo `src/main/resources/application.properties` con los siguientes valores:

```properties
server.port=8080
spring.datasource.url=jdbc:mysql://localhost:3306/digital_store
spring.datasource.username=root
spring.datasource.password=mysql
spring.datasource.driver-class=com.mysql.jdbc.Driver

# spring.mvc.pathmatch.matching-strategy = ANT_PATH_MATCHER
```

### 4. Construir e iniciar la aplicación

```bash
mvn clean install
mvn spring-boot:run
```

## Uso de la API

### Endpoints principales

#### (Pendiente)

## Pruebas con Postman

Para probar la API, puedes importar la colección de Postman ubicada en el repositorio (`postman_collection.json`).

## Licencia

#### (Pendiente)

