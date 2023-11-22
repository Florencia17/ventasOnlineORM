package ar.unrn.tp.ui;

import ar.unrn.tp.jpa.service.ClienteServicio;
import ar.unrn.tp.jpa.service.VentaServicio;
import ar.unrn.tp.modelo.Producto;
import ar.unrn.tp.modelo.Venta;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class VentanaCompras extends JFrame {

    private JPanel contentPane;
    private VentaServicio ventaService;

    private Long idCliente;

    private List<Venta> ventas = new ArrayList<>();

    private DefaultListModel<Venta> modeloVentas = new DefaultListModel();
//
//    /**
//     * Launch the application.
//     */
//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    VentanaCompras frame = new VentanaCompras();
//                    frame.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

    /**
     * Create the frame.
     */
    public VentanaCompras(VentaServicio ventaServicio, Long idCliente) {

        this.ventaService = ventaServicio;
        this.idCliente = idCliente;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        DefaultListModel<Venta> modeloVentas = new DefaultListModel();
        JList listVentas = new JList();
        listVentas.setModel(modeloVentas);
        listVentas.setBounds(24, 37, 385, 143);
//        JScrollPane scrollPane = new JScrollPane(listProductos);
//        scrollPane.setBounds(30, 52, 337, 159);
        contentPane.add(listVentas);

        JLabel ventasNewLabel = new JLabel("Ultimas 3 ventas del cliente:");
        ventasNewLabel.setForeground(new Color(0, 0, 0));
        ventasNewLabel.setFont(new Font("Franklin Gothic Demi", Font.PLAIN, 14));
        ventasNewLabel.setBounds(135, 11, 187, 15);
        contentPane.add(ventasNewLabel);

        JButton listarVentasNewButton = new JButton("Listar ventas");
        listarVentasNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                modeloVentas.removeAllElements();
                ventas = ventaService.ultimasVentas(idCliente);

                for (Venta p: ventas) {

                    modeloVentas.addElement(p);
                }
            }


        });
        listarVentasNewButton.setBounds(24, 191, 188, 23);
        contentPane.add(listarVentasNewButton);


//        JPanel panel = new JPanel();
//        panel.setBounds(10, 45, 238, 112);
//        contentPane.add(panel);



    }
}