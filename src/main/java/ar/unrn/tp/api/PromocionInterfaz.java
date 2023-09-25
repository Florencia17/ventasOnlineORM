package ar.unrn.tp.api;

import ar.unrn.tp.modelo.MarcaPromocion;
import ar.unrn.tp.modelo.TarjetaPromocion;

import java.time.LocalDate;

public interface PromocionInterfaz {

    // validar que las fechas no se superpongan
    void crearDescuentoSobreTotal(String marcaTarjeta, LocalDate fechaDesde,
                                  LocalDate fechaHasta, float porcentaje);

    // validar que las fechas no se superpongan
    void crearDescuento(String marcaProducto, LocalDate fechaDesde, LocalDate
            fechaHasta, float porcentaje);

    void crearTienda();

    //agrega la marca a la tienda
    void agregarPromocionMarca(MarcaPromocion marcaPromocion);

    void agregarPromocionTarjeta(TarjetaPromocion tarjetaPromocion);

}