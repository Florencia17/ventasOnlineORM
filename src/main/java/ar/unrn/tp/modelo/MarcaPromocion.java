package ar.unrn.tp.modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("PROMOCION_MARCA")
public class MarcaPromocion extends Promocion {
    private String marca;
    public MarcaPromocion(boolean estado, LocalDate fechaInicio, LocalDate fechaFin, double porcentaje, String nombre) {
        super(estado, fechaInicio, fechaFin, porcentaje);
        this.marca = nombre;
    }

    public String getMarca(){
        return marca;
    }



    public MarcaPromocion(boolean estado, LocalDate fechaInicio, LocalDate fechaFin,  String marca) {
        super(estado, fechaInicio, fechaFin);
        this.marca = marca;
    }

    public MarcaPromocion(){

    }

    @Override
    public double descuento(String tipo) {
        return 0;
    }

    public String marca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }


    @Override
    public double descuento() {
        LocalDate hoy = LocalDate.now();
        if (hoy.isAfter(this.fechaFin()) || hoy.isBefore(this.fechaInicio())){
            return 0;
        }
        return 0.05;
    }




   /* @Override
    public String toString() {
        return "MarcaPromocion{ " + marca +" }";
    }*/
}
