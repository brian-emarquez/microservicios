package com.brian.productos.service;

import com.brian.productos.model.Producto;
import com.brian.productos.repository.ProductoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository repository;

    public ProductoService(ProductoRepository repository) {
        this.repository = repository;
    }

    public List<Producto> listar() {
        return repository.findAll();
    }

    public Producto buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Producto " + id + " no encontrado"));
    }

    public Producto crear(Producto producto) {
        producto.setId(null);
        return repository.save(producto);
    }

    public Producto actualizar(Long id, Producto datos) {
        Producto existente = buscar(id);
        existente.setNombre(datos.getNombre());
        existente.setPrecio(datos.getPrecio());
        existente.setStock(datos.getStock());
        return repository.save(existente);
    }

    public void eliminar(Long id) {
        buscar(id);
        repository.deleteById(id);
    }
}