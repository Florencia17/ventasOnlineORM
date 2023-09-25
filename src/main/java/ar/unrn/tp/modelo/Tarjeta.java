package ar.unrn.tp.modelo;


import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Tarjeta {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private int numero;
    private TipoTarjeta tipo;

    @Enumerated(EnumType.STRING) TipoTarjeta tipoTarjeta;

    protected Tarjeta(){

    }

    public Tarjeta(int numero, TipoTarjeta tipo) {
        this.numero = numero;
        this.tipo = tipo;
    }

    public Tarjeta(long id, int numero, TipoTarjeta tipo){
        this.id= id;
        this.numero= numero;
        this.tipo= tipo;
    }
    public TipoTarjeta tipoTarjeta(){
        return tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setTipo(TipoTarjeta tipo) {
        this.tipoTarjeta = tipo;
    }

   /* public int getNumero() {
        return numero;
    }*/

//    public Map<String, Object> toMap() {
//        HashMap<String, Object> map = new HashMap<String, Object>(
//                Map.of("id", id, "numero", numero, "tipo", tipoTarjeta));
//
//        return map;
//    }


    @Override
    public String toString() {
        return "Tarjeta{" +
                "numero=" + numero +
                ", tipo=" + tipo +
                '}';
    }
}
