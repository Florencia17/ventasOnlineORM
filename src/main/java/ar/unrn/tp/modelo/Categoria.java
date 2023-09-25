package ar.unrn.tp.modelo;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;

    protected Categoria() {} //o public?

    public Categoria(String nombre) {

        this.nombre = nombre;
    }

    public long getId(){
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

      @Override
    public String toString() {
        return "Categoria: " + nombre+ "id: "+ id;
    }
}
