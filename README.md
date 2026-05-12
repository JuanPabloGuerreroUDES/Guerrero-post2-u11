# Guerrero-post2-u11
## Juan Pablo Guerrero Hernandez - 0223013209

**Programación Web — Unidad 11: Buenas Prácticas y Patrones de Diseño**  
Post-Contenido 2 — Logging con SLF4J/Logback y Documentación con Swagger/OpenAPI  
Ingeniería de Sistemas — Universidad de Santander (UDES) — 2026

---

## Descripción

Aplicación Spring Boot que implementa un catálogo de productos REST con:
- **SLF4J + Logback**: logging estructurado con niveles INFO/WARN/ERROR/DEBUG y rotación diaria de archivos.
- **springdoc-openapi 2.3.0**: documentación interactiva de la API con Swagger UI.
- **Principios SOLID**, patrón DAO/DTO, Factory y manejo global de errores con `@ControllerAdvice`.

---

## Tecnologías

| Tecnología | Versión |
|---|---|
| Java | 17 |
| Spring Boot | 3.2.0 |
| springdoc-openapi | 2.3.0 |
| H2 Database | (en memoria) |
| Logback | (incluido en spring-boot-starter-web) |
| Maven | 3.x |

---

## Ejecución

### Prerrequisitos
- Java 17+
- Maven 3.6+

### Pasos

```bash
# 1. Clonar el repositorio
git clone https://github.com/<usuario>/Guerrero-post2-u11.git
cd Guerrero-post2-u11

# 2. Compilar y ejecutar
mvn spring-boot:run
```

La aplicación inicia en `http://localhost:8080`.

---

## URLs importantes

| Recurso | URL |
|---|---|
| **Swagger UI** | http://localhost:8080/swagger-ui.html |
| **OpenAPI JSON** | http://localhost:8080/api-docs |
| **H2 Console** | http://localhost:8080/h2-console |
| **API Base** | http://localhost:8080/api/productos |

---

## Endpoints disponibles

| Método | URL | Descripción |
|---|---|---|
| `POST` | `/api/productos` | Crear un nuevo producto |
| `GET` | `/api/productos` | Listar todos los productos |
| `GET` | `/api/productos/{id}` | Buscar producto por ID |
| `GET` | `/api/productos/categoria/{cat}` | Filtrar por categoría |
| `PUT` | `/api/productos/{id}` | Actualizar un producto |
| `DELETE` | `/api/productos/{id}` | Eliminar un producto |

### Categorías válidas
`ELECTRONICA`, `PAPELERIA`, `HOGAR`, `ROPA`, `DEPORTES`

### Ejemplo de solicitud POST

```json
POST /api/productos
Content-Type: application/json

{
  "nombre": "Laptop HP ProBook 450 G9",
  "precio": 3500000.00,
  "categoria": "ELECTRONICA",
  "descripcion": "Laptop empresarial con Intel Core i5, 8GB RAM, 256GB SSD",
  "stock": 15
}
```

---

## Archivos de Log

Los logs se generan en la carpeta `logs/` (excluida del repositorio vía `.gitignore`):

| Archivo | Descripción |
|---|---|
| `logs/catalogo.log` | Log actual del día en ejecución |
| `logs/catalogo.YYYY-MM-DD.log` | Logs históricos con rotación diaria |

La carpeta `logs/` se crea automáticamente al iniciar la aplicación.

### Niveles configurados
- `com.empresa.catalogo` → **DEBUG** (incluye trazas de búsqueda)
- Global → **INFO**

### Ejemplo de salida en consola (Checkpoint 1)
```
14:32:01 INFO  c.e.c.s.impl.ProductoServiceImpl - Creando producto: nombre=Laptop HP ProBook 450 G9, categoria=ELECTRONICA, precio=3500000.0
14:32:01 INFO  c.e.c.s.impl.ProductoServiceImpl - Producto creado exitosamente con id=1
14:32:05 DEBUG c.e.c.s.impl.ProductoServiceImpl - Buscando producto con id=1
14:32:05 DEBUG c.e.c.s.impl.ProductoServiceImpl - Producto con id=1 encontrado: nombre=Laptop HP ProBook 450 G9
```

### Ejemplo de salida en archivo (Checkpoint 2)
```
2026-05-12 14:32:01 INFO  com.empresa.catalogo.service.impl.ProductoServiceImpl - Creando producto: nombre=Laptop HP ProBook 450 G9, categoria=ELECTRONICA, precio=3500000.0
2026-05-12 14:32:01 INFO  com.empresa.catalogo.service.impl.ProductoServiceImpl - Producto creado exitosamente con id=1
```

---

## Estructura del Proyecto

```
Guerrero-post2-u11/
├── src/
│   └── main/
│       ├── java/com/empresa/catalogo/
│       │   ├── CatalogoApplication.java          # Clase principal + @OpenAPIDefinition
│       │   ├── controller/
│       │   │   └── ProductoController.java        # @Tag, @Operation, @ApiResponse
│       │   ├── service/
│       │   │   ├── ProductoService.java           # Interfaz (SOLID - DIP)
│       │   │   └── impl/
│       │   │       └── ProductoServiceImpl.java   # Logging SLF4J completo
│       │   ├── repository/
│       │   │   └── ProductoRepository.java        # Spring Data JPA
│       │   ├── model/
│       │   │   └── Producto.java                  # Entidad JPA
│       │   ├── dto/
│       │   │   ├── ProductoRequestDTO.java        # @Schema en campos
│       │   │   ├── ProductoResponseDTO.java
│       │   │   └── ErrorResponseDTO.java
│       │   ├── factory/
│       │   │   └── ProductoFactory.java           # Conversión Entity ↔ DTO
│       │   └── exception/
│       │       ├── RecursoNoEncontradoException.java
│       │       └── GlobalExceptionHandler.java    # @ControllerAdvice
│       └── resources/
│           ├── application.properties             # Config springdoc
│           └── logback-spring.xml                 # Config Logback (Checkpoint 2)
├── logs/                                          # Generado en runtime (.gitignore)
├── .gitignore
├── pom.xml
└── README.md
```

---

## Checkpoints de Evaluación

### ✅ Checkpoint 1 — SLF4J en el Servicio
Ejecutar la aplicación y realizar operaciones:
```bash
# Crear producto (POST)
curl -X POST http://localhost:8080/api/productos \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Laptop HP","precio":3500000,"categoria":"ELECTRONICA","stock":10}'

# Buscar producto (GET)
curl http://localhost:8080/api/productos/1
```
Los mensajes aparecen en consola con formato `HH:mm:ss LEVEL Logger - mensaje`.

### ✅ Checkpoint 2 — Archivo de Log con Rotación
Tras las operaciones anteriores, verificar:
```bash
cat logs/catalogo.log
```
El archivo existe con formato `yyyy-MM-dd HH:mm:ss LEVEL Logger - mensaje`.

### ✅ Checkpoint 3 — Swagger UI
Abrir en el navegador: **http://localhost:8080/swagger-ui.html**

La interfaz muestra el grupo **"Productos"** con 6 endpoints documentados, cada uno con sus códigos de respuesta (200, 201, 400, 404) y esquemas de ejemplo.

---

## Commits descriptivos sugeridos

```
git commit -m "feat: estructura inicial del proyecto Maven con Spring Boot 3.2"
git commit -m "feat: implementación de SLF4J en ProductoServiceImpl con niveles INFO/WARN/ERROR"
git commit -m "feat: configuración de Logback con appenders CONSOLA y ARCHIVO con rotación diaria"
git commit -m "feat: documentación Swagger con @OpenAPIDefinition, @Tag, @Operation y @Schema"
git commit -m "docs: README con instrucciones de ejecución y evidencias de checkpoints"
```
