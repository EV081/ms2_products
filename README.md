# Microservicio 2: Gestión de Productos y Categorías

Servicio REST construido con **Spring Boot** para gestionar Productos y sus Categorías.

Base de datos en **MySQL 8** con integridad referencial: cada producto pertenece a una categoría.

---

## Relaciones entre tablas

**Productos → Categorías: Relación N:1.**
Un producto pertenece a una categoría.

### Tablas y relaciones

**Productos (N:1 con Categorías)**

* `id_producto` (PK, INT, AUTO_INCREMENT)
* `nombre` (VARCHAR(100))
* `descripcion` (TEXT)
* `precio` (DECIMAL(10, 2))
* `categoria_id` (FK a Categorías, INT)

**Categorías**

* `id_categoria` (PK, INT, AUTO_INCREMENT)
* `nombre_categoria` (VARCHAR(100))
* `descripcion_categoria` (TEXT)

---

## Endpoints

### Healthcheck

* **GET /healthcheck** → Devuelve un mensaje con código `200` indicando que el backend está activo.

### Documentación Swagger

* **GET /swagger-ui/index.html** → Devuelve la documentación generada automáticamente de la API.

### Productos

* **GET /productos** → Obtener todos los productos.
* **GET /productos/{id_producto}** → Obtener detalles de un producto.
* **POST /productos** → Crear un nuevo producto.
* **PUT /productos/{id_producto}** → Actualizar un producto.
* **DELETE /productos/{id_producto}** → Eliminar un producto.

### Categorías

* **GET /categorias** → Obtener todas las categorías.
* **GET /categorias/{id_categoria}** → Obtener detalles de una categoría.
* **POST /categorias** → Crear una nueva categoría.
* **PUT /categorias/{id_categoria}** → Actualizar una categoría.
* **DELETE /categorias/{id_categoria}** → Eliminar una categoría.

---

## Variables de entorno (`.env`)

```env
# Spring Boot
SPRING_APPLICATION_NAME=ms2_productos
SERVER_PORT=8080

# Postgres
DB_HOST=
DB_PORT=
DB_NAME=
DB_USERNAME=
DB_PASSWORD=
CORS_ALLOWED_ORIGINS=


# Hibernate / JPA
spring.datasource.url=jdbc:postgresql://${DB_HOST}:${DB_PORT}/${DB_NAME}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

cors.allowed-origins=${CORS_ALLOWED_ORIGINS}
```

---

3. **Acceder a la API**

   * Healthcheck → [http://localhost:8080/healthcheck](http://localhost:8080/healthcheck)
   * Swagger → [http://localhost:8080/docs](http://localhost:8080/docs)

---

👉 ¿Quieres que también te prepare la parte de **DTOs** (como en tu ejemplo de FastAPI con Pydantic, pero en Java con Lombok/records) para que quede completo?
