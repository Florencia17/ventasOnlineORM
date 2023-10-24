package ar.unrn.tp.ui;



//import ar.unrn.tp.api.MarcaService;
import ar.unrn.tp.api.ProductoInterfaz;
import ar.unrn.tp.api.VentaInterface;
import ar.unrn.tp.modelo.Marca;
import ar.unrn.tp.modelo.Producto;
import ar.unrn.tp.modelo.Categoria;
import ar.unrn.tp.modelo.Marca;
//import ar.unrn.tp.modelo.exceptions.BusinessException;
//import jakarta.persistence.OptimisticLockException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModificarProductosUI extends JFrame {
        private ProductoInterfaz productoService;
        private Marca marca;
        private JPanel contentPane;
        private JTextField nombreTextField, precioTextField, marcaTextField;
        private JComboBox<Categoria> categoriaComboBox;
        private JLabel idLabel;
        private Producto producto;

        public ModificarProductosUI(ProductoInterfaz ps, Long id) {
                modificar(ps, id);
        }
        public void modificar(ProductoInterfaz ps, Long id){
                this.productoService = ps;
                producto = productoService.buscarProducto(24L); //el id va por el main
//                productoDTO = productoService.encontrarProducto(1L).getDTO();
                initJFrame();
        }

        public void initJFrame(){
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setTitle("Formulario de Producto");
                setSize(300, 200);
                setLocationRelativeTo(null);
                setBounds(100, 100, 450, 300);

                initPanel();
                setVisible(true);
        }
        public void initPanel(){
                // Crear un panel
                contentPane = new JPanel(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.anchor = GridBagConstraints.WEST;
                gbc.insets = new Insets(5, 5, 5, 5);

                // Etiqueta para ID
                JLabel id = new JLabel("ID del Producto:");
                gbc.gridx = 0;
                gbc.gridy = 0;
                contentPane.add(id,gbc);
                idLabel = new JLabel(String.valueOf(producto.getId()));
                idLabel.setForeground(Color.BLUE);
                gbc.gridx = 1;
                gbc.gridy = 0;
                contentPane.add(idLabel,gbc);

                // Etiqueta y campo de texto para el nombre
                JLabel nombreLabel = new JLabel("Nombre:");
                gbc.gridx = 0;
                gbc.gridy = 1;
                contentPane.add(nombreLabel, gbc);
                nombreTextField = new JTextField(20);
                nombreTextField.setText(producto.descripcion()); //descripcion o get descripcion
                gbc.gridx = 1;
                gbc.gridy = 1;
                contentPane.add(nombreTextField,gbc);

                //Etiqueta y campo de texto para el codigo
                JLabel codigoLabel = new JLabel("Codigo:");
                gbc.gridx = 0;
                gbc.gridy = 2; //1
                contentPane.add(codigoLabel, gbc);
                JTextField codigoTextField = new JTextField(20);
                codigoTextField.setText(producto.codigo());
                gbc.gridx = 1;
                gbc.gridy = 2; //1
                contentPane.add(codigoTextField,gbc);

                // Etiqueta y campo de texto para el precio
                JLabel precioLabel = new JLabel("Precio:");
                gbc.gridx = 0;
                gbc.gridy = 3; //2
                contentPane.add(precioLabel,gbc);

                precioTextField = new JTextField(20);
                precioTextField.setText(String.valueOf(producto.precio()));
                gbc.gridx = 1;
                gbc.gridy = 3; //2
                contentPane.add(precioTextField,gbc);
                // Etiqueta y campo de texto para la marca
                // gbc.gridx = 0;
                // gbc.gridy = 4; //3
//               gbc.gridx = 1;
//                gbc.gridy = 4; //3
                JLabel marcaLabel = new JLabel("Marca:");
                gbc.gridx = 0;
                gbc.gridy = 4; //3
                JComboBox<Object> marcaComboBox = new JComboBox<>();
                List<Marca> marcas = productoService.listarMarcas();
                for(Marca m : marcas){
                        marcaComboBox.addItem(m);
                }
                contentPane.add(marcaLabel,gbc);
                gbc.gridx = 1;
                gbc.gridy = 4; //3
                contentPane.add(marcaComboBox,gbc);
//                marcaTextField = new JTextField(20);
////               marcaTextField.setText(producto.marca().toString());  //ver si funciona
//                String textoMarca = marcaTextField.getText();
//                contentPane.add(marcaTextField,gbc);

                // Etiqueta y combo para la categoría
                JLabel categoriaLabel = new JLabel("Categoría:");
                gbc.gridx = 0;
                gbc.gridy = 5; //4
                categoriaComboBox = new JComboBox<>();
                List<Categoria> categorias = productoService.listarCategorias();
//                if (categorias.isEmpty()) {
//                        JOptionPane.showMessageDialog(null, "no existen categorias para listar", "Error",
//                                JOptionPane.ERROR_MESSAGE);
//                        return;
//                }
                for (Categoria c : categorias) {
                        categoriaComboBox.addItem(c);
                }
//                categoriaComboBox.setSelectedItem(producto.getCategoria());
//                JComboBox comboBoxCategoria = new JComboBox();
                contentPane.add(categoriaLabel,gbc);
                gbc.gridx = 1;
                gbc.gridy = 5; //4
                contentPane.add(categoriaComboBox,gbc);

//                private void cargarCategorias() {
//                       List<Categoria> categorias1 = productoService.listarCategorias();
//                        if (categorias.isEmpty()) {
//                                JOptionPane.showMessageDialog(null, "no existen categorias para listar", "Error",
//                                        JOptionPane.ERROR_MESSAGE);
//                                return;
//                        }
//                        for (Categoria c : categorias) {
//                                categoriaComboBox.addItem(c);
//                        }
//                        categoriaComboBox.setSelectedItem(producto.getCategoria());
//                }


//                public void modificarProducto(Long idProducto, String codigo, String descripcion, float precio, Long IdCategoria,
//                        Long IdMarca, Long version)

//                productoService.modificarProducto(producto.getId(), textCodigo.getText(), textDescripcion.getText(),
//                        precio, ((Categoria) comboCategoria.getSelectedItem()).getId(),
//                       c, producto.getVersion());


                // Botón Guardar
                JButton guardarButton = new JButton("Guardar");
                guardarButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                try{
//                                        String textoMarca = marcaTextField.getText();
                                        productoService.modificarProducto(24L, codigoTextField.getText() ,nombreTextField.getText(),
                                                Float.parseFloat(precioTextField.getText()),
                                                ((Categoria) categoriaComboBox.getSelectedItem()).getId(),  ((Marca) marcaComboBox.getSelectedItem()).getId(), producto.getVersion());
                                        JOptionPane.showMessageDialog(null, "Producto modificado correctamente");
                                }catch (RuntimeException ex){
                                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                                        return;
                                }
                        }
                });
                contentPane.add(guardarButton);
                add(contentPane);
        }

}

























//import ar.unrn.tp.api.ProductoInterfaz;
//import ar.unrn.tp.jpa.service.ProductoServicio;
//import ar.unrn.tp.modelo.Categoria;
//import ar.unrn.tp.modelo.Marca;
//import ar.unrn.tp.modelo.Producto;
//
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JTextField;
//import javax.swing.JComboBox;
//import javax.swing.JButton;
//import java.util.List;
//
//public class ModificarProductosUI extends JFrame {
//    private JTextField txtIngreseElNuevo;
//    private JTextField txtIngreseElNuevo_1;
//
//    private ProductoInterfaz productoService;
//
//    private Producto producto;
//    private Long id;
//
//    private List<Marca> marcas;
//    private List<Categoria> categorias;
//
//    public ModificarProductosUI(ProductoServicio ps, Long id) {
//        modificar(ps, id);
//
//    }
//
//    void modificar(ProductoServicio ps, Long id){
//            this.productoService = ps;
//            producto = productoService.buscarProducto(id);
//
//
//            getContentPane().setLayout(null);
//
//            JLabel lblNewLabel = new JLabel("ID del producto a modificar:");
//            lblNewLabel.setBounds(10, 11, 155, 22);
//            getContentPane().add(lblNewLabel);
//
//            JLabel lblNewLabel_ID = new JLabel("New label");
//            lblNewLabel_ID.setBounds(153, 15, 46, 14);
//            getContentPane().add(lblNewLabel_ID);
//
//            JLabel lblNewLabel_2 = new JLabel("Nombre del producto:");
//            lblNewLabel_2.setBounds(10, 44, 114, 14);
//            getContentPane().add(lblNewLabel_2);
//
//            txtIngreseElNuevo = new JTextField();
//            txtIngreseElNuevo.setText("Ingrese el nuevo nombre");
//            txtIngreseElNuevo.setBounds(10, 62, 139, 20);
//            getContentPane().add(txtIngreseElNuevo);
//            txtIngreseElNuevo.setColumns(10);
//
//            JLabel lblNewLabel_Nombre = new JLabel("New label");
//            lblNewLabel_Nombre.setBounds(129, 44, 95, 14);
//            getContentPane().add(lblNewLabel_Nombre);
//
//            JLabel lblNewLabel_4 = new JLabel("Precio:");
//            lblNewLabel_4.setBounds(10, 95, 52, 14);
//            getContentPane().add(lblNewLabel_4);
//
//            JLabel lblNewLabel_Precio = new JLabel("New label");
//            lblNewLabel_Precio.setBounds(72, 95, 46, 14);
//            getContentPane().add(lblNewLabel_Precio);
//
//            txtIngreseElNuevo_1 = new JTextField();
//            txtIngreseElNuevo_1.setText("Ingrese el nuevo precio");
//            txtIngreseElNuevo_1.setBounds(10, 120, 130, 20);
//            getContentPane().add(txtIngreseElNuevo_1);
//            txtIngreseElNuevo_1.setColumns(10);
//
//            JLabel lblNewLabel_6 = new JLabel("Categoría del producto:");
//            lblNewLabel_6.setBounds(10, 154, 130, 14);
//            getContentPane().add(lblNewLabel_6);
//
//            JLabel lblNewLabel_7 = new JLabel("Marca del producto:");
//            lblNewLabel_7.setBounds(213, 154, 103, 14);
//            getContentPane().add(lblNewLabel_7);
//
//            JComboBox comboBoxCategoria = new JComboBox();
//            comboBoxCategoria.setToolTipText("");
//            comboBoxCategoria.setBounds(10, 206, 30, 22);
//            getContentPane().add(comboBoxCategoria);
//
//            JComboBox comboBox_Marca = new JComboBox();
//            comboBox_Marca.setBounds(213, 206, 30, 22);
//            getContentPane().add(comboBox_Marca);
//
//            JLabel lblNewLabel_8 = new JLabel("Actual:");
//            lblNewLabel_8.setBounds(10, 179, 46, 14);
//            getContentPane().add(lblNewLabel_8);
//
//            JLabel lblNewLabel_8_1 = new JLabel("Actual:");
//            lblNewLabel_8_1.setBounds(212, 179, 46, 14);
//            getContentPane().add(lblNewLabel_8_1);
//
//            JButton btnNewButton = new JButton("Guardar");
//            btnNewButton.setBounds(335, 227, 89, 23);
//            getContentPane().add(btnNewButton);
//
//            JLabel lblNewLabel_CategoriaActual = new JLabel("New label");
//            lblNewLabel_CategoriaActual.setBounds(50, 179, 46, 14);
//            getContentPane().add(lblNewLabel_CategoriaActual);
//
//            JLabel lblNewLabel_MarcaActual = new JLabel("New label");
//            lblNewLabel_MarcaActual.setBounds(257, 179, 46, 14);
//            getContentPane().add(lblNewLabel_MarcaActual);
//        }
//        }
