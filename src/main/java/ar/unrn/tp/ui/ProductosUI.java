package ar.unrn.tp.ui;

import ar.unrn.tp.api.ClienteInterfaz;
import ar.unrn.tp.api.ProductoInterfaz;
import ar.unrn.tp.api.VentaInterface;
import ar.unrn.tp.jpa.service.ClienteServicio;
import ar.unrn.tp.jpa.service.ProductoServicio;
import ar.unrn.tp.jpa.service.VentaServicio;
import ar.unrn.tp.modelo.Producto;



import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class ProductosUI extends JFrame {

    private JPanel contentPane;
    private JButton agregarAlCarritoNewButton;

    private ProductoServicio productoService;
    private VentaServicio ventaService;
    private ClienteServicio clienteService;
    private Long idCliente;

    private List<Producto> productos = new ArrayList<>();


    private DefaultListModel<Producto> modeloProductosC = new DefaultListModel();


    /**
     * Launch the application.
     */

    /*
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ProductosUI frame = new ProductosUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
*/
    /**
     * Create the frame.
     */

    public ProductosUI(ProductoServicio ps, VentaServicio vs, ClienteServicio cs, Long idCliente) {
        this.productoService = ps; //cambiar nombre a interfaz
        this.ventaService = vs;
        this.clienteService = cs;
        this.idCliente = idCliente;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        DefaultListModel<Producto> modeloProductos = new DefaultListModel();
        JList listProductos = new JList();
        listProductos.setModel(modeloProductos);
        listProductos.setBounds(24, 37, 385, 143);
//        JScrollPane scrollPane = new JScrollPane(listProductos);
//        scrollPane.setBounds(30, 52, 337, 159);
        contentPane.add(listProductos);

        JLabel listaDeProductosNewLabel = new JLabel("Lista de Productos: ");
        listaDeProductosNewLabel.setForeground(new Color(0, 0, 0));
        listaDeProductosNewLabel.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 14));
        listaDeProductosNewLabel.setBounds(135, 11, 187, 15);
        contentPane.add(listaDeProductosNewLabel);

        agregarAlCarritoNewButton = new JButton("Agregar al Carrito");
        agregarAlCarritoNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int[] index = listProductos.getSelectedIndices();
                if (index.length == 0) {
                    JOptionPane.showMessageDialog(null, "Debe seleccionar al menos un producto", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                for (int i = 0; i < index.length; i++) {

                    modeloProductosC.addElement(modeloProductos.getElementAt(index[i]));
                }

            }
        });
        agregarAlCarritoNewButton.setBounds(222, 191, 187, 23);
        contentPane.add(agregarAlCarritoNewButton);

        JButton listarProductosNewButton = new JButton("Listar productos");
        listarProductosNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                modeloProductos.removeAllElements();
                productos = productoService.listarProductos();

                for (Producto p : productos) {

                    modeloProductos.addElement(p);
                }
            }


        });
        listarProductosNewButton.setBounds(24, 191, 188, 23);
        contentPane.add(listarProductosNewButton);

        JButton irAlCarritoNewButton = new JButton("Ir al Carrito");
        irAlCarritoNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TarjetasUI tarjetasUI = new TarjetasUI(new ProductoServicio("jpa-mysql"), new VentaServicio("jpa-mysql"), new ClienteServicio("jpa-mysql"), 1L, modeloProductosC);
                tarjetasUI.setVisible(true);
            }
        });
        irAlCarritoNewButton.setBounds(105, 225, 187, 23);
        contentPane.add(irAlCarritoNewButton);



    }
}
