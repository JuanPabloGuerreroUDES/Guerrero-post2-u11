package com.empresa.catalogo.factory;

import com.empresa.catalogo.dto.ProductoRequestDTO;
import com.empresa.catalogo.dto.ProductoResponseDTO;
import com.empresa.catalogo.model.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoFactory {

    public Producto toEntity(ProductoRequestDTO dto) {
        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());
        producto.setCategoria(dto.getCategoria().toUpperCase());
        producto.setDescripcion(dto.getDescripcion());
        producto.setStock(dto.getStock());
        return producto;
    }

    public ProductoResponseDTO toResponseDTO(Producto producto) {
        return new ProductoResponseDTO(
            producto.getId(),
            producto.getNombre(),
            producto.getPrecio(),
            producto.getCategoria(),
            producto.getDescripcion(),
            producto.getStock()
        );
    }
}
