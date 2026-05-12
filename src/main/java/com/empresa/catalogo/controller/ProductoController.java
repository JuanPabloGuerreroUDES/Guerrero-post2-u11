package com.empresa.catalogo.controller;

import com.empresa.catalogo.dto.ErrorResponseDTO;
import com.empresa.catalogo.dto.ProductoRequestDTO;
import com.empresa.catalogo.dto.ProductoResponseDTO;
import com.empresa.catalogo.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@Tag(name = "Productos", description = "Operaciones CRUD del catálogo de productos")
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    // ================================================
    // POST /api/productos — Crear producto
    // ================================================
    @Operation(
        summary = "Crear un nuevo producto",
        description = "Registra un nuevo producto en el catálogo con todos sus datos. " +
                      "El nombre, precio, categoría y stock son obligatorios."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Producto creado exitosamente",
            content = @Content(schema = @Schema(implementation = ProductoResponseDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos de entrada inválidos (validación fallida)",
            content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
        )
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductoResponseDTO crear(
            @Valid @RequestBody ProductoRequestDTO dto) {
        return service.crear(dto);
    }

    // ================================================
    // GET /api/productos — Listar todos
    // ================================================
    @Operation(
        summary = "Listar todos los productos",
        description = "Retorna la lista completa de productos registrados en el catálogo."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de productos retornada exitosamente",
            content = @Content(schema = @Schema(implementation = ProductoResponseDTO.class))
        )
    })
    @GetMapping
    public List<ProductoResponseDTO> listarTodos() {
        return service.listarTodos();
    }

    // ================================================
    // GET /api/productos/{id} — Buscar por ID
    // ================================================
    @Operation(
        summary = "Obtener producto por ID",
        description = "Busca y retorna un producto específico usando su identificador único."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Producto encontrado exitosamente",
            content = @Content(schema = @Schema(implementation = ProductoResponseDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Producto no encontrado con el ID proporcionado",
            content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
        )
    })
    @GetMapping("/{id}")
    public ProductoResponseDTO buscarPorId(
            @Parameter(description = "ID único del producto", example = "1", required = true)
            @PathVariable Long id) {
        return service.buscarPorId(id);
    }

    // ================================================
    // GET /api/productos/categoria/{categoria}
    // ================================================
    @Operation(
        summary = "Listar productos por categoría",
        description = "Filtra y retorna los productos que pertenecen a la categoría indicada."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de productos de la categoría retornada exitosamente",
            content = @Content(schema = @Schema(implementation = ProductoResponseDTO.class))
        )
    })
    @GetMapping("/categoria/{categoria}")
    public List<ProductoResponseDTO> listarPorCategoria(
            @Parameter(
                description = "Nombre de la categoría",
                example = "ELECTRONICA",
                schema = @Schema(allowableValues = {"ELECTRONICA", "PAPELERIA", "HOGAR", "ROPA", "DEPORTES"})
            )
            @PathVariable String categoria) {
        return service.listarPorCategoria(categoria);
    }

    // ================================================
    // PUT /api/productos/{id} — Actualizar
    // ================================================
    @Operation(
        summary = "Actualizar un producto existente",
        description = "Actualiza todos los campos de un producto identificado por su ID."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Producto actualizado exitosamente",
            content = @Content(schema = @Schema(implementation = ProductoResponseDTO.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos de entrada inválidos",
            content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Producto no encontrado con el ID proporcionado",
            content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> actualizar(
            @Parameter(description = "ID del producto a actualizar", example = "1", required = true)
            @PathVariable Long id,
            @Valid @RequestBody ProductoRequestDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    // ================================================
    // DELETE /api/productos/{id} — Eliminar
    // ================================================
    @Operation(
        summary = "Eliminar un producto",
        description = "Elimina permanentemente un producto del catálogo usando su ID."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Producto eliminado exitosamente (sin contenido)"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Producto no encontrado con el ID proporcionado",
            content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class))
        )
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(
            @Parameter(description = "ID del producto a eliminar", example = "1", required = true)
            @PathVariable Long id) {
        service.eliminar(id);
    }
}
