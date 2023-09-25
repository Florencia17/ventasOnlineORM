package ar.unrn.tp.modelo;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("PROMOCION_TARJETA")
public class TarjetaPromocion extends Promocion{

    private String tarjeta;


    public TarjetaPromocion(boolean estado, LocalDate fechaInicio, LocalDate fechaFin, double porcentaje, String tarjeta) throws RuntimeException {
        super(estado, fechaInicio, fechaFin, porcentaje);
        try{
            this.tarjeta = TipoTarjeta.valueOf(tarjeta.toUpperCase()).toString();

        }catch (IllegalArgumentException e){
            throw new RuntimeException("la tarjeta no corresponde al tipo tarjeta");
        }

    }

    protected TarjetaPromocion() {
        super();

    }

    public String getTarjeta(){
        return tarjeta;
    }



    @Override
    public double descuento(String tipo) {
        LocalDate hoy = LocalDate.now();
        if (!this.tarjeta.equals(tipo) || hoy.isAfter(this.fechaFin()) || hoy.isBefore(this.fechaInicio())){
            return 0;
        }
        return 0.08;
    }

    @Override
    public double descuento() {
        return 0;
    }


    public String tarjeta() {
        return tarjeta;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }


    public String toString() {
        return (super.toString() + "TarjetaPromocion{ " + tarjeta + " }");
    }

    /*@Override
    public String toString() {
        return "TarjetaPromocion{ "+ tarjeta +" }";
    }*/
}
