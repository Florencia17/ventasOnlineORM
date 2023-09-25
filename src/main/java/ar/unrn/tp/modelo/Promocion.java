package ar.unrn.tp.modelo;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
@Entity
@Inheritance
@DiscriminatorColumn(name = "tipo_promocion")
public abstract class Promocion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private boolean estado;
    private Date fechaInicio;
    private Date fechaFin;

    private double descuento;



    public Promocion(boolean estado, LocalDate fechaInicio, LocalDate fechaFin, double descuento) {
        this.estado = estado;
        this.descuento= descuento;
        System.out.println(fechaInicio);
        System.out.println(fechaFin);
        if (this.validarFecha(fechaInicio, fechaFin)) {
            this.fechaInicio = Date.from(fechaInicio.atStartOfDay(ZoneId.systemDefault()).toInstant());
            this.fechaFin = Date.from(fechaFin.atStartOfDay(ZoneId.systemDefault()).toInstant());
        } else {
            throw new RuntimeException("Las fechas de la promocion no son validas." + fechaInicio + "|||||" + fechaFin);
        }

        System.out.println(this.fechaInicio);
        System.out.println(this.fechaFin);

    }

    public Promocion(boolean estado, LocalDate fechaInicio, LocalDate fechaFin) {
        this.estado = estado;
        System.out.println(fechaInicio);
        System.out.println(fechaFin);
        if (this.validarFecha(fechaInicio, fechaFin)) {
            this.fechaInicio = Date.from(fechaInicio.atStartOfDay(ZoneId.systemDefault()).toInstant());
            this.fechaFin = Date.from(fechaFin.atStartOfDay(ZoneId.systemDefault()).toInstant());
        } else {
            throw new RuntimeException("Las fechas de la promocion no son validas." + fechaInicio + "|||||" + fechaFin);
        }

        System.out.println(this.fechaInicio);
        System.out.println(this.fechaFin);

    }

    protected Promocion() {

    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado() {
        this.estado = !this.estado;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }


    private Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    protected double getDescuento() {
        return this.descuento;
    }


    public LocalDate fechaInicio() {
        return fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public LocalDate fechaFin() {
        return fechaFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    private boolean validarFecha(LocalDate fechaInicio, LocalDate fechaFin) {
        LocalDate hoy = LocalDate.now();
        return (fechaInicio.isBefore(fechaFin) && hoy.isBefore(fechaFin));
    }

    public boolean fechaValida() {
        System.out.println(this.fechaInicio);
        System.out.println(this.fechaFin);
        LocalDate hoy = LocalDate.now();
        LocalDate inicio = this.fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fin = this.fechaFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return (inicio.isBefore(fin) && hoy.isBefore(fin)); //si se cumplen ambas y retorna true, es fecha valida
    }

    public abstract double descuento(String tipo);
    public abstract double descuento();

    @Override
    public String toString() {
        return "Datos" + " {" +
                "estado=" + estado +
                ", fechaInicio=" + fechaInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() +
                ", fechaFin=" + fechaFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDate() +
                '}';


    }
}


