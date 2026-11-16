package Vista;

import javax.swing.*;
import java.awt.*;

public class InventarioVista extends JFrame {

    public JTextField nombre = new JTextField();
    public JTextField cantidad = new JTextField();
    public JButton registrar = new JButton("Registrar / Reponer");
    public JButton vender = new JButton("Vender");
    public JButton inventario = new JButton("Ver inventario");
    public JButton critico = new JButton("Ver stock critico");
    public JTextArea resultado = new JTextArea();

    public InventarioVista() {
        setTitle("Almacen - Inventario");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        resultado.setEditable(false);

        JPanel arriba = new JPanel(new GridLayout(4, 2));
        arriba.add(new JLabel("Nombre del producto:"));
        arriba.add(nombre);
        arriba.add(new JLabel("Cantidad:"));
        arriba.add(cantidad);
        arriba.add(registrar);
        arriba.add(vender);
        arriba.add(inventario);
        arriba.add(critico);

        add(arriba, BorderLayout.NORTH);
        add(new JScrollPane(resultado), BorderLayout.CENTER);
    }
}
