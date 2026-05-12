package com.empresa.catalogo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Schema(description = "Datos de entrada para crear o actualizar un producto")
public class ProductoRequestDTO {

    @Schema(
        description = "Nombre del producto",
        example = "Laptop HP ProBook 450 G9",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Schema(
        description = "Precio del producto en pesos colombianos",
        example = "3500000.00",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor a cero")
    private Double precio;

    @Schema(
        description = "Categoría del producto",
        allowableValues = {"ELECTRONICA", "PAPELERIA", "HOGAR", "ROPA", "DEPORTES"},
        example = "ELECTRONICA",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "La categoría es obligatoria")
    private String categoria;

    @Schema(
        description = "Descripción detallada del producto",
        example = "Laptop empresarial con procesador Intel Core i5, 8GB RAM, 256GB SSD"
    )
    private String descripcion;

    @Schema(
        description = "Cantidad disponible en inventario",
        example = "15",
        requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "El stock es obligatorio")
    @PositiveOrZero(message = "El stock no puede ser negativo")
    private Integer stock;

    // Getters y Setters
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
