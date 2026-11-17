package Vista;

import javax.swing.*;
import java.awt.*;

/**
 * Construye la ventana y los controles para administrar el inventario.
 */
public class InventarioVista extends JFrame {

    /** Campo para escribir el nombre del producto. */
    public JTextField nombre = new JTextField();

    /** Campo para escribir la cantidad que se registrará o venderá. */
    public JTextField cantidad = new JTextField();

    /** Botón para registrar un producto o reponer sus existencias. */
    public JButton registrar = new JButton("Registrar / Reponer");

    /** Botón para vender unidades de un producto. */
    public JButton vender = new JButton("Vender");

    /** Botón para mostrar todos los productos y existencias. */
    public JButton inventario = new JButton("Ver inventario");

    /** Botón para mostrar los productos con stock crítico. */
    public JButton critico = new JButton("Ver stock critico");

    /** Área donde se presentan los mensajes y resultados de las operaciones. */
    public JTextArea resultado = new JTextArea();

    /**
     * Inicializa y organiza los componentes de la ventana.
     */
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
