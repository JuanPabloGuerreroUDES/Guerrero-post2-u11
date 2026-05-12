package com.empresa.catalogo.service.impl;

import com.empresa.catalogo.dto.ProductoRequestDTO;
import com.empresa.catalogo.dto.ProductoResponseDTO;
import com.empresa.catalogo.exception.RecursoNoEncontradoException;
import com.empresa.catalogo.factory.ProductoFactory;
import com.empresa.catalogo.model.Producto;
import com.empresa.catalogo.repository.ProductoRepository;
import com.empresa.catalogo.service.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

    // Logger estático SLF4J — Checkpoint 1
    private static final Logger log = LoggerFactory.getLogger(ProductoServiceImpl.class);

    private final ProductoRepository repo;
    private final ProductoFactory factory;

    public ProductoServiceImpl(ProductoRepository repo, ProductoFactory factory) {
        this.repo = repo;
        this.factory = factory;
    }

    @Override
    public ProductoResponseDTO crear(ProductoRequestDTO dto) {
        log.info("Creando producto: nombre={}, categoria={}, precio={}",
                 dto.getNombre(), dto.getCategoria(), dto.getPrecio());

        if (repo.existsByNombre(dto.getNombre())) {
            log.warn("Intento de crear producto con nombre duplicado: nombre={}", dto.getNombre());
        }

        Producto p = factory.toEntity(dto);
        Producto guardado = repo.save(p);
        ProductoResponseDTO resp = factory.toResponseDTO(guardado);

        log.info("Producto creado exitosamente con id={}", resp.getId());
        return resp;
    }

    @Override
    public ProductoResponseDTO buscarPorId(Long id) {
        log.debug("Buscando producto con id={}", id);

        Producto p = repo.findById(id).orElseThrow(() -> {
            log.warn("Producto con id={} no encontrado", id);
            return new RecursoNoEncontradoException("Producto", id);
        });

        log.debug("Producto con id={} encontrado: nombre={}", id, p.getNombre());
        return factory.toResponseDTO(p);
    }

    @Override
    public List<ProductoResponseDTO> listarTodos() {
        log.info("Listando todos los productos del catálogo");

        List<Producto> productos = repo.findAll();

        if (productos.isEmpty()) {
            log.warn("El catálogo no contiene productos registrados");
        } else {
            log.info("Catálogo retornado con total={} productos", productos.size());
        }

        return productos.stream()
                        .map(factory::toResponseDTO)
                        .collect(Collectors.toList());
    }

    @Override
    public List<ProductoResponseDTO> listarPorCategoria(String categoria) {
        log.info("Listando productos por categoria={}", categoria);

        List<Producto> productos = repo.findByCategoria(categoria.toUpperCase());

        log.info("Productos encontrados en categoria={}: total={}", categoria, productos.size());

        return productos.stream()
                        .map(factory::toResponseDTO)
                        .collect(Collectors.toList());
    }

    @Override
    public ProductoResponseDTO actualizar(Long id, ProductoRequestDTO dto) {
        log.info("Actualizando producto con id={}", id);

        Producto existente = repo.findById(id).orElseThrow(() -> {
            log.warn("Producto con id={} no encontrado para actualización", id);
            return new RecursoNoEncontradoException("Producto", id);
        });

        existente.setNombre(dto.getNombre());
        existente.setPrecio(dto.getPrecio());
        existente.setCategoria(dto.getCategoria().toUpperCase());
        existente.setDescripcion(dto.getDescripcion());
        existente.setStock(dto.getStock());

        ProductoResponseDTO resp = factory.toResponseDTO(repo.save(existente));
        log.info("Producto con id={} actualizado correctamente: nombre={}", id, resp.getNombre());

        return resp;
    }

    @Override
    public void eliminar(Long id) {
        log.info("Eliminando producto con id={}", id);

        // Verifica existencia antes de eliminar
        buscarPorId(id);

        repo.deleteById(id);
        log.info("Producto con id={} eliminado correctamente", id);
    }
}
