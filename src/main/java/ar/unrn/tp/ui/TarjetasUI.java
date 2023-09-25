package ar.unrn.tp.ui;


import ar.unrn.tp.api.ClienteInterfaz;
import ar.unrn.tp.api.ProductoInterfaz;
import ar.unrn.tp.api.VentaInterface;
import ar.unrn.tp.jpa.service.ClienteServicio;
import ar.unrn.tp.jpa.service.ProductoServicio;
import ar.unrn.tp.jpa.service.VentaServicio;
import ar.unrn.tp.modelo.Producto;
import ar.unrn.tp.modelo.Tarjeta;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
//import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TarjetasUI extends JFrame {

    private JPanel contentPane;
    private ProductoInterfaz productoService;
    private VentaInterface ventaServicio;
    private ClienteInterfaz clienteService;
    private Long idCliente;

    private List<Tarjeta> tarjetas = new ArrayList<>();

    private Tarjeta tarjetaSeleccionada;

    private DefaultListModel<Producto> listprodu;

    /**
     * Launch the application.
     */
    /*
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TarjetasUI frame = new TarjetasUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */

    public TarjetasUI(ProductoServicio ps, VentaServicio vs, ClienteServicio cs, Long idCliente, DefaultListModel<Producto> lp) {
        this.productoService = ps; //cambiar nombre a interfaz
        this.ventaServicio = vs;
        this.clienteService = cs;
        this.idCliente = idCliente;
        this.listprodu = lp;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);




        DefaultListModel<Tarjeta> modeloTarjeta = new DefaultListModel();


        JList listTarjetas = new JList();
        listTarjetas.setModel(modeloTarjeta);
        listTarjetas.setBounds(10, 36, 256, 63);
        contentPane.add(listTarjetas);

        JLabel tarjetasNewLabel = new JLabel("Seleccione una tarjeta para realizar la compra:");
        tarjetasNewLabel.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 14));
        tarjetasNewLabel.setBounds(10, 11, 306, 14);
        contentPane.add(tarjetasNewLabel);

        JLabel tarjetaSelecNewLabel = new JLabel("");
        tarjetaSelecNewLabel.setBounds(178, 104, 231, 14);
        contentPane.add(tarjetaSelecNewLabel);


        JButton seleccionarTarjetaNewButton = new JButton("Seleccionar");
        seleccionarTarjetaNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                int index = listTarjetas.getSelectedIndex();
                if (index == -1) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar una tarjeta", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                tarjetaSeleccionada = modeloTarjeta.getElementAt(index);
                //ver de mostrar tarjeta seleccionada
                tarjetaSelecNewLabel.setText(tarjetaSeleccionada.toString());

            }


        });
        seleccionarTarjetaNewButton.setBounds(284, 70, 140, 23);
        contentPane.add(seleccionarTarjetaNewButton);

        JButton listTarjetasNewButton = new JButton("Mostrar mis tarjetas");
        listTarjetasNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modeloTarjeta.removeAllElements();
                tarjetas = clienteService.listarTarjetas(idCliente);
                for (Tarjeta t : tarjetas) {
                    modeloTarjeta.addElement(t);
                }
            }
        });
        listTarjetasNewButton.setBounds(284, 36, 140, 23);
        contentPane.add(listTarjetasNewButton);

        JList listCarrito = new JList();
        listCarrito.setModel(lp);
        listCarrito.setBounds(10, 135, 256, 102);
        contentPane.add(listCarrito);


        JLabel carritoListNewLabel = new JLabel("Productos del carrito:");
        carritoListNewLabel.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 14));
        carritoListNewLabel.setBounds(10, 110, 179, 14);
        contentPane.add(carritoListNewLabel);


        JButton montoTotalNewButton = new JButton("Ver Monto Total");
        montoTotalNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //PONER PROMOS VALIDAS

                try {
                    List<Long> productosCompra = new ArrayList();

                    for (int i = 0; i < lp.getSize(); i++) {
                        productosCompra.add(lp.get(i).getId());
                    }
                    if (tarjetaSeleccionada == null) {
                        JOptionPane.showMessageDialog(null, "Debe seleccionar una tarjeta", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    JOptionPane.showMessageDialog(null,
                            "El monto total es : "
                                    + ventaServicio.calcularMonto(productosCompra, tarjetaSeleccionada.getId()),
                            "Monto", JOptionPane.INFORMATION_MESSAGE);

                } catch (RuntimeException e1) {
                    JOptionPane.showMessageDialog(null, "Error: " + e1.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        });
        montoTotalNewButton.setBounds(284, 143, 140, 23);
        contentPane.add(montoTotalNewButton);

        JButton comprarNewButton = new JButton("Comprar");
        comprarNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Long> productosCompra = new ArrayList();

                    for (int i = 0; i < lp.getSize(); i++) {
                        productosCompra.add(lp.get(i).getId());
                    }
                    if (tarjetaSeleccionada == null) {
                        JOptionPane.showMessageDialog(null, "Debe seleccionar una tarjeta", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    ventaServicio.realizarVenta(idCliente, productosCompra, tarjetaSeleccionada.getId());
                    JOptionPane.showMessageDialog(null, "La venta se realizo correctametne", "Exito",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (RuntimeException e1) {
                    JOptionPane.showMessageDialog(null, "Error: " + e1.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

            }
        });
        comprarNewButton.setBounds(284, 189, 140, 23);
        contentPane.add(comprarNewButton);
    }

}
