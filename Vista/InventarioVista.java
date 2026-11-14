
package Vista;

import javax.swing.*;
import java.awt.*;

public class InventarioVista extends JFrame {

    public InventarioVista() {

        setTitle("Almacen - Inventario");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        JPanel campos = new JPanel(new GridLayout(2, 2));

        JLabel nombre = new JLabel("Nombre del producto:");
        JTextField campoNombre = new JTextField();

        JLabel cantidad = new JLabel("Cantidad:");
        JTextField campoCantidad = new JTextField();

        campos.add(nombre);
        campos.add(campoNombre);
        campos.add(cantidad);
        campos.add(campoCantidad);

        JPanel botones = new JPanel(new GridLayout(2, 2));

        JButton registrar = new JButton("Registrar / Reponer");
        JButton vender = new JButton("Vender");
        JButton inventario = new JButton("Ver inventario");
        JButton critico = new JButton("Ver stock critico");

        botones.add(registrar);
        botones.add(vender);
        botones.add(inventario);
        botones.add(critico);

        JPanel superior = new JPanel(new BorderLayout());
        superior.add(campos, BorderLayout.NORTH);
        superior.add(botones, BorderLayout.SOUTH);

        String[] columnas = {"Producto", "Stock"};
        String[][] datos = {};

        JTable tabla = new JTable(datos, columnas);

        JLabel estado = new JLabel("Inventario completo: 0 productos");

        panel.add(superior, BorderLayout.NORTH);
        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);
        panel.add(estado, BorderLayout.SOUTH);

        add(panel);
    }
}
