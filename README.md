# Microservicio 2: Gestión de Productos y Categorías

Servicio REST construido con **Spring Boot** para gestionar Productos y sus Categorías.

Base de datos en **MySQL 8** con integridad referencial: cada producto pertenece a una categoría.

---

## 📋 Tablas y Relaciones

### Relación entre tablas
**Productos → Categorías: Relación N:1**  
Un producto pertenece a una categoría.

### Estructura de tablas

#### **Categorías**
| Campo | Tipo | Descripción |
|-------|------|-------------|
| `id_categoria` | INT, AUTO_INCREMENT | Clave primaria |
| `nombre_categoria` | VARCHAR(100) | Nombre de la categoría |
| `descripcion_categoria` | TEXT | Descripción de la categoría |

#### **Productos**
| Campo | Tipo | Descripción |
|-------|------|-------------|
| `id_producto` | INT, AUTO_INCREMENT | Clave primaria |
| `nombre` | VARCHAR(100) | Nombre del producto |
| `descripcion` | TEXT | Descripción del producto |
| `precio` | DECIMAL(10, 2) | Precio del producto |
| `categoria_id` | INT | Clave foránea a Categorías |

---

🏗️ DTOs (Data Transfer Objects)
CategoriaDTO
java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO {
    private Long idCategoria;
    private String nombreCategoria;
    private String descripcionCategoria;
}
ProductoRequestDTO (Para creación/actualización)
java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoRequestDTO {
    private String nombre;
    private String descripcion;
    private double precio;
    private Long idCategoria;
}
ProductoResponseDTO (Para respuestas)
java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoResponseDTO {
    private Long idProducto;
    private String nombre;
    private String descripcion;
    private double precio;
    private CategoriaDTO categoria; 
}
PaginatedResponse (Para paginación)
java
@Data
@NoArgsConstructor
public class PaginatedResponse<T> {
    private List<T> contents;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
}


## 🚀 Endpoints de la API

### Healthcheck & Documentación
- **GET** `/healthcheck` → Verifica que el servicio esté activo
- **GET** `/swagger-ui/index.html` → Documentación interactiva de la API

### Productos

#### **GET /productos**
Obtiene la lista de todos los productos.


#### **GET /productos/{id_producto}**
Obtiene un producto específico por su ID.


#### **POST /productos**
Crea un nuevo producto.

**Ejemplo de solicitud:**
```json
{
  "nombre": "Producto B",
  "descripcion": "Descripción del Producto B",
  "precio": 100.0,
  "categoria": {
    "idCategoria": 1
  }
}
```

#### **PUT /productos/{id_producto}**
Actualiza un producto existente.

**Ejemplo de solicitud:**
```json
{
  "nombre": "Producto A Actualizado",
  "descripcion": "Descripción actualizada",
  "precio": 120.0,
  "categoria": {
    "idCategoria": 1
  }
}
```

#### **DELETE /productos/{id_producto}**
Elimina un producto.


### Categorías

#### **GET /categorias**
Obtiene la lista de todas las categorías.

**Ejemplo de respuesta:**
```json
[
  {
    "id_categoria": 1,
    "nombre_categoria": "Categoría A",
    "descripcion_categoria": "Descripción de la Categoría A"
  }
]
```

#### **GET /categorias/{id_categoria}**
Obtiene una categoría específica por su ID.

#### **POST /categorias**
Crea una nueva categoría.

**Ejemplo de solicitud:**
```json
{
  "nombreCategoria": "Categoría A",
  "descripcionCategoria": "Descripción de Categoría A"
}
```

#### **PUT /categorias/{id_categoria}**
Actualiza una categoría existente.
```json
[
  {
    "nombreCategoria": "Categoría A Actualizada", 
    "descripcionCategoria": "Descripción actualizada de la Categoría A"
  }
]
```

#### **DELETE /categorias/{id_categoria}**
Elimina una categoría.


### 🔗 Productos por Categoría

#### **GET /categorias/{id_categoria}/productos**
Obtiene todos los productos de una categoría específica.

**Ejemplo de respuesta:**
```json
[
  {
    "id_producto": 1,
    "nombre": "Producto A",
    "descripcion": "Descripción del Producto A",
    "precio": 100.0
  }
]
```

---

## ⚙️ Configuración

### Variables de Entorno (`.env`)

```env
# Spring Boot
SPRING_APPLICATION_NAME=ms2_productos
SERVER_PORT=8080

# Base de Datos
DB_HOST=localhost
DB_PORT=3306
DB_NAME=productos_db
DB_USERNAME=usuario
DB_PASSWORD=contraseña

# Hibernate / JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# CORS
CORS_ALLOWED_ORIGINS=http://localhost:3000
```

---

## 🛠️ Instalación y Uso

1. **Clonar y configurar el proyecto**
2. **Configurar las variables de entorno** en el archivo `.env`
3. **Ejecutar la aplicación Spring Boot**
4. **Acceder a los endpoints:**
   - Healthcheck: `http://localhost:8080/health`
   - Swagger UI: `http://localhost:8080/swagger-ui/index.html`

---

## 📚 Tecnologías Utilizadas

- **Spring Boot** - Framework principal
- **MySQL 8** - Base de datos
- **Spring Data JPA** - Persistencia de datos
- **Spring Web** - API REST
- **Swagger/OpenAPI** - Documentación
- **Lombok** - Reducción de código boilerplate

---
