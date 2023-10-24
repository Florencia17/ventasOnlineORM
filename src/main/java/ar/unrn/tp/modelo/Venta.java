package ar.unrn.tp.modelo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Cliente cliente;
    @Enumerated(EnumType.ORDINAL)
    private EstadoVenta estadoVenta;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Tarjeta tarjeta;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_venta")
    private List<ProductoVendido> productosVendidos;


    //Se agrega el numero de venta
    private String numeroVenta;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public EstadoVenta getEstadoVenta() {
        return estadoVenta;
    }

    public void setEstadoVenta(EstadoVenta estadoVenta) {
        this.estadoVenta = estadoVenta;
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }

    public List<ProductoVendido> getProductosVendidos() {
        return productosVendidos;
    }

    public void setProductosVendidos(List<ProductoVendido> productosVendidos) {
        this.productosVendidos = productosVendidos;
    }

    public double getMontoAbonado() {
        return montoAbonado;
    }

    public void setMontoAbonado(double montoAbonado) {
        this.montoAbonado = montoAbonado;
    }
    private double montoAbonado;



    protected Venta() {

    }


    public Venta(Cliente cliente, Tarjeta tarjeta, EstadoVenta estadoVenta, List<Producto> productosVendidos, double montoAbonado) {
        this.cliente = cliente;
        this.tarjeta = tarjeta;
        this.estadoVenta = estadoVenta;
        this.productosVendidos = new ArrayList<>();
        this.agregarProductos(productosVendidos);
        this.montoAbonado = montoAbonado;
    }


    //Constructor con el numero de venta + los get y set
    public Venta(Cliente cliente, Tarjeta tarjeta, EstadoVenta estadoVenta, List<Producto> productosVendidos, double montoAbonado, String numeroVenta) {
        this(cliente, tarjeta, estadoVenta, productosVendidos, montoAbonado);
        this.numeroVenta = numeroVenta;
    }

    public String getNumeroVenta() {
        return numeroVenta;
    }

    public void setNumeroVenta(String numeroVenta) {
        this.numeroVenta = numeroVenta;
    }

    private void agregarProductos(List<Producto> productos) {
        productos.forEach(producto ->
                this.productosVendidos.add(
                        new ProductoVendido(producto.codigo(), producto.precio())));

    }

    @Override
    public String toString() {
        return "Venta{" +
                "cliente=" + cliente +
                ", estadoVenta=" + estadoVenta +
                ", tarjeta=" + tarjeta +
                ", productosVendidos=" + productosVendidos +
                ", montoAbonado=" + montoAbonado +
                '}';
    }

    public void tocarProductoVendido(){
        this.productosVendidos.size();
    }

}
