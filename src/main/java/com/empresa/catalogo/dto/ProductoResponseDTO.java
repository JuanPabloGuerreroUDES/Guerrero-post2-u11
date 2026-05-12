package com.empresa.catalogo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Datos de respuesta de un producto")
public class ProductoResponseDTO {

    @Schema(description = "Identificador único del producto", example = "1")
    private Long id;

    @Schema(description = "Nombre del producto", example = "Laptop HP ProBook 450 G9")
    private String nombre;

    @Schema(description = "Precio en pesos colombianos", example = "3500000.00")
    private Double precio;

    @Schema(description = "Categoría del producto", example = "ELECTRONICA")
    private String categoria;

    @Schema(description = "Descripción del producto", example = "Laptop empresarial con procesador Intel Core i5")
    private String descripcion;

    @Schema(description = "Cantidad en inventario", example = "15")
    private Integer stock;

    // Constructor vacío
    public ProductoResponseDTO() {}

    public ProductoResponseDTO(Long id, String nombre, Double precio,
                               String categoria, String descripcion, Integer stock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.stock = stock;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
}
