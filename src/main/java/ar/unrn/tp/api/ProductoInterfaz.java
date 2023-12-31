package ar.unrn.tp.api;

import ar.unrn.tp.modelo.Producto;

import java.util.List;

public interface ProductoInterfaz {

    //validar que sea una categoría existente y que codigo no se repita
    void crearProducto(String codigo, String descripcion, float precio, Long IdCategoria, Long IdMarca);

    //validar que sea un producto existente
    void modificarProducto(Long idProducto, String codigo, String descripcion, float precio, Long idCategoria,
                           Long idMarca);

    //Devuelve todos los productos
    List<Producto> listarProductos();
    void crearCategoria(String nombre);
    void crearMarca(String nombre);
}
