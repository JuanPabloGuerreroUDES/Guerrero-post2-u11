package com.empresa.catalogo.service;

import com.empresa.catalogo.dto.ProductoRequestDTO;
import com.empresa.catalogo.dto.ProductoResponseDTO;

import java.util.List;

/**
 * Contrato del servicio de productos.
 * Define las operaciones CRUD disponibles para el catálogo.
 */
public interface ProductoService {

    ProductoResponseDTO crear(ProductoRequestDTO dto);

    ProductoResponseDTO buscarPorId(Long id);

    List<ProductoResponseDTO> listarTodos();

    List<ProductoResponseDTO> listarPorCategoria(String categoria);

    ProductoResponseDTO actualizar(Long id, ProductoRequestDTO dto);

    void eliminar(Long id);
}
