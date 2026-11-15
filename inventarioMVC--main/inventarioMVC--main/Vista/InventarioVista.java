
package Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

public class InventarioVista extends JFrame {

    private JTextField txtCampoNombre;
    private JTextField txtCampoCantidad;
    private JButton btnRegistrar;
    private JButton btnVender;
    private JButton btnInventario;
    private JButton btnCritico;
    private DefaultTableModel modeloTabla;
    private JLabel estado;

    public InventarioVista() {

        setTitle("Almacen - Inventario");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        JPanel pnlCampos = new JPanel(new GridLayout(2, 2));

        JLabel lblNombre = new JLabel("Nombre del producto:");
        txtCampoNombre = new JTextField();

        JLabel lblCantidad = new JLabel("Cantidad:");
        txtCampoCantidad = new JTextField();

        pnlCampos.add(lblNombre);
        pnlCampos.add(txtCampoNombre);
        pnlCampos.add(lblCantidad);
        pnlCampos.add(txtCampoCantidad);

        JPanel pnlBotones = new JPanel(new GridLayout(2, 2));

        btnRegistrar = new JButton("Registrar / Reponer");
        btnVender = new JButton("Vender");
        btnInventario = new JButton("Ver inventario");
        btnCritico = new JButton("Ver stock critico");

        pnlBotones.add(btnRegistrar);
        pnlBotones.add(btnVender);
        pnlBotones.add(btnInventario);
        pnlBotones.add(btnCritico);

        JPanel superior = new JPanel(new BorderLayout());
        superior.add(pnlCampos, BorderLayout.NORTH);
        superior.add(pnlBotones, BorderLayout.SOUTH);

        String[] columnas = {"Producto", "Stock"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable tabla = new JTable(modeloTabla);

        estado = new JLabel("Inventario completo: 0 productos");

        panel.add(superior, BorderLayout.NORTH);
        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);
        panel.add(estado, BorderLayout.SOUTH);

        add(panel);
    }

    public String getNombreProducto() {
        return txtCampoNombre.getText();
    }

    public int getCantidadProducto() {
        try {
            return Integer.parseInt(txtCampoCantidad.getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public void actualizarTabla(Map<String, Integer> productos) {
        modeloTabla.setRowCount(0);
        for (Map.Entry<String, Integer> producto : productos.entrySet()) {
            modeloTabla.addRow(new Object[]{producto.getKey(), producto.getValue()});
        }
        estado.setText("Inventario completo: " + productos.size() + " productos");
    }

    public void limpiarCampos() {
        txtCampoNombre.setText("");
        txtCampoCantidad.setText("");
        txtCampoNombre.requestFocusInWindow();
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Datos inválidos", JOptionPane.ERROR_MESSAGE);
    }

    public void setRegistrarListener(ActionListener listener) {
        btnRegistrar.addActionListener(listener);
    }

    public void setVenderListener(ActionListener listener) {
        btnVender.addActionListener(listener);
    }

    public void setInventarioListener(ActionListener listener) {
        btnInventario.addActionListener(listener);
    }

    public void setCriticoListener(ActionListener listener) {
        btnCritico.addActionListener(listener);
    }
}
