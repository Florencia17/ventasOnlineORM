package ar.unrn.tp.main;
import ar.unrn.tp.modelo.*;
import ar.unrn.tp.jpa.service.*;
import ar.unrn.tp.api.*;
import ar.unrn.tp.api.ClienteInterfaz;
import ar.unrn.tp.jpa.service.ClienteServicio;
import ar.unrn.tp.jpa.service.ProductoServicio;
import ar.unrn.tp.jpa.service.PromocionServicio;
import ar.unrn.tp.jpa.service.VentaServicio;
import ar.unrn.tp.ui.ModificarProductosUI;
import ar.unrn.tp.ui.ProductosUI;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        ClienteInterfaz clienteServicio = new ClienteServicio("jpa-mysql");
//        ProductoServicio productoService = new ProductoServicio("jpa-mysql");
//        PromocionServicio promocionService = new PromocionServicio("jpa-mysql");
//        VentaServicio ventaService = new VentaServicio("jpa-mysql");
//        kjdshfhsfkj

        VentaInterface vs = new VentaServicio("jpa-mysql");
//        List<Venta> ventas = vs.ventas();
//        System.out.println("VENTAS: " + ventas);


//        ClienteInterfaz cs = new ClienteServicio("jpa-mysql");
//        cs.crearCliente("florencia", "malacarne", "41421287", "flor@gmail.com");
       //crear otro cliente
//        cs.modificarCliente(1L, "f", "mala", "41421287", "flor@gmail.com");
//      cs.agregarTarjeta(1L, "1", "mastercard");
//        cs.agregarTarjeta(1L, "12", "visa");
//       System.out.println(cs.listarTarjetas(1L));
//
//      ProductoServicio ps = new ProductoServicio("jpa-mysql");
//       ps.crearMarca("garnier");
//
//        ps.crearCategoria("shampoo");

//        ps.crearProducto("1", "bebida con gas", 200, 10L, 9L);

//       ps.modificarProducto(24L, "2", "bebida con gas", 250, 10L, 9L);
//        System.out.println(ps.listarProductos());
//
//        PromocionInterfaz promo = new PromocionServicio("jpa-mysql");
//            promo.crearTienda();

//        promo.crearDescuentoSobreTotal("mastercard",
//                    LocalDate.now().minusDays(2), LocalDate.now().plusDays(2), (float) 0.08);
//            promo.crearDescuento("cocacola",
//                    LocalDate.now().minusDays(2), LocalDate.now().plusDays(2), (float) 0.05);

        PromocionInterfaz promo = new PromocionServicio("jpa-mysql");
//        MarcaPromocion mp = new MarcaPromocion( true, LocalDate.now(), LocalDate.now().plusDays(3), 0.05, "cocacola");
//        TarjetaPromocion tp= new TarjetaPromocion(true, LocalDate.now(), LocalDate.now().plusDays(4), 0.08, "MASTERCARD");
////        promo.agregarPromocionMarca(mp);
//        promo.agregarPromocionTarjeta(tp);
//        ProductosUI ventanaProductos = new ProductosUI(new ProductoServicio("jpa-mysql"), new VentaServicio("jpa-mysql"), new ClienteServicio("jpa-mysql"), 1L );
//        ventanaProductos.setVisible(true);

        ModificarProductosUI frame = new ModificarProductosUI(new ProductoServicio("jpa-mysql"), 24L);
        frame.setVisible(true);






//        DescuentoServicio ds= new DescuentoServicio();
//        ds.crearTienda();
//       ds.crearDescuentoSobreTotal("MASTERCARD", LocalDate.now().minusDays(3),
//               LocalDate.now().plusDays(5), 0.8f);
//
//        ds.crearDescuento("cocacola", LocalDate.now().minusDays(2),
//                LocalDate.now().plusDays(10), 0.5f);


//        ps.crearMarca("garnier");
//        ps.crearCategoria("shampoo");

//        ps.crearProducto("12", "sin parabenos", 1000 , 13L, 12L);
//
//        VentaServicio vs = new VentaServicio();
//        List<Long> productos = new ArrayList<>();
//        productos.add(7L);
//        productos.add(14L);
////
////        vs.realizarVenta(1L,productos, 4L);
//
//        System.out.println(vs.calcularMonto(productos, 4L));
//        System.out.println(vs.ventas());

//        ProductosUI ventanaProductos = new ProductosUI(new ProductoServicio(), new VentaServicio(), new ClienteServicio(), 1L );
//        ventanaProductos.setVisible(true);

    }
}
