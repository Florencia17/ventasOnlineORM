package ar.unrn.tp.modelo;


import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Producto {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    @Version
    private long version;
    private String codigo;
    private String descripcion;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Categoria categoria;
    private double precio;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Marca marca;

    protected Producto() {

    }
    public Producto(String codigo, double precio, String descripcion, Categoria categoria, Marca marca) {
        if (esDatoVacio(codigo))
            throw new RuntimeException("El codigo debe ser valido");
        this.codigo = codigo;

        if (descripcion == null || descripcion.isEmpty())
            throw new RuntimeException("La descripcion debe ser valido");
        this.descripcion = descripcion;

        if (esDatoVacio(String.valueOf(precio)))
            throw new RuntimeException("El precio debe ser valido");
        this.precio = precio;

        if (esDatoNulo(categoria))
            throw new RuntimeException("La categoria debe ser valido");
        this.categoria = categoria;

        if (esDatoNulo(marca))
            throw new RuntimeException("La marca debe ser valido");
        this.marca = marca;
    }

    public Producto(long id, String codigo, double precio, String descripcion, Categoria categoria, Marca marca) throws RuntimeException{
        this(codigo, precio, descripcion, categoria, marca);
        this.id = id;
    }


    //Otra opcion para el manejo de versiones
    public Producto(Long id, String codigo, String descripcion, Categoria categoria, double precio, Marca marca,
                    Long version) throws RuntimeException {
        this(codigo, precio, descripcion, categoria, marca);
        this.id = id;
        this.version = version;
    }


    private boolean esDatoVacio(String dato) {
        return dato.equals("");
    }

    private boolean esDatoNulo(Object dato) {
        return dato == null;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
    public String codigo() {
        return codigo;
    }

    public String descripcion() {
        return descripcion;
    }

    public Marca marca() {
        return marca;
    }

    public Categoria categoria() {
        return categoria;
    }

    public double precio() {
        return precio;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public Marca getMarca() {
        return marca;
    }

    public Long getId() {
        return id;
    }


    private void setId(Long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Producto{" +
                "codigo=" + codigo +
                ", descripcion='" + descripcion + '\'' +
                ", " + categoria +
                ", precio=" + precio +
                ", " + marca +
                '}';
    }

//    public Map<String, Object> toMap() {
//        HashMap<String, Object> map = new HashMap<String, Object>(
//                Map.of("id", id, "codigo", codigo, "precio", precio, "descripcion", descripcion,
//                        "categoria", categoria, "marca", marca));
//
//        return map;
//    }
}