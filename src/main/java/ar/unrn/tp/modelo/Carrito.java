package ar.unrn.tp.modelo;

import java.util.ArrayList;
import java.util.List;

public class Carrito {

    private List<Producto> productos;

    public List<Producto> productos() {
        return productos;
    }

    public Carrito() {
        this.productos = new ArrayList<>();
        //    this.cliente = cliente;
    }

    public void agregarProductoAlCarrito(Producto producto) {
        if (producto == null)
            throw new RuntimeException("El producto a agregar no puede ser vacio.");
        this.productos.add(
                new Producto(producto.codigo(), producto.precio(),
                        producto.descripcion(), producto.categoria(),
                        producto.marca()));
    }

    //Crear otra clase para agregar los productos al carrito, Productos Vendidos. duplicar.


    public double calcularMontoCarrito(Promocion marcaPromocion, Promocion tarjetaPromocion, Tarjeta tarjeta) { //verificar si funciona bien!

        if (marcaPromocion == null || tarjetaPromocion == null)
            throw new RuntimeException("Las promociones no pueden ser vacias.");
        if (productos == null)
            throw new RuntimeException("El carrito debe contener como minimo un producto");
        if (tarjeta == null)
            throw new RuntimeException("La tarjeta para calcular el monto no puede ser vacia.");

        double precio = 0;
        for (Producto producto : this.productos) {
//            if (producto.marca().equals(marcaPromocion.marca())) {
//
//                precio = precio + (producto.precio() - (producto.precio() * marcaPromocion.descuento()));
//            } else {
//                precio += producto.precio();
//            }
            precio = precio + (producto.precio() -
                    (producto.precio() * marcaPromocion.descuento(producto.marca().getNombre())));
        }
//        if (tarjeta.tipoTarjeta().equals(tarjetaPromocion.tarjeta())) {
//            precio = precio - (precio * tarjetaPromocion.descuento());
        precio = precio - (precio * tarjetaPromocion.descuento(tarjeta.tipoTarjeta().nombre()));



        return precio;

    }

    public Venta pagar(Cliente cliente, Promocion marcaPromocion, Promocion tarjetaPromocion, Tarjeta tarjeta) {

        if (productos == null)
            throw new RuntimeException("Debe existir como minimo un producto en el carrito.");

        if (tarjeta != null) {
            return new Venta(cliente, tarjeta, EstadoVenta.COMPLETA, productos, calcularMontoCarrito(marcaPromocion, tarjetaPromocion, tarjeta));
        }
        return new Venta(cliente, tarjeta, EstadoVenta.CANCELADA, productos, 0);
    }

   /* @Override
    public String toString() {
        return "Carrito{ " + productos +
                " }";
    }*/
}
