package Controlador;

import Modelo.InventarioModelo;
import Vista.InventarioVista;

/**
 * Coordina las acciones de la vista con las operaciones disponibles
 * en el modelo del inventario.
 */
public class InventarioControlador {

    /**
    * Configura los botones de la vista para solicitar operaciones al modelo
    * y presentar sus resultados en la interfaz.
     *
     * Parametro vista, interfaz cuyos controles se configuran
     * Parametro modelo,
     * inventario que procesa las operaciones solicitadas
     */
    public InventarioControlador(InventarioVista vista, InventarioModelo modelo) {

        // Registra un producto o repone sus unidades; después, limpia los campos.
        vista.registrar.addActionListener(e -> {
            try {
                String producto = vista.nombre.getText();
                int numero = Integer.parseInt(vista.cantidad.getText());
                vista.resultado.setText(modelo.registrar(producto, numero));
            } catch (NumberFormatException error) {
                vista.resultado.setText("Ingrese una cantidad entera valida.");
            } finally {
                vista.nombre.setText("");
                vista.cantidad.setText("");
            }
        });

        // Procesa la venta solicitada y limpia los campos al finalizar.
        vista.vender.addActionListener(e -> {
            try {
                String producto = vista.nombre.getText();
                int numero = Integer.parseInt(vista.cantidad.getText());
                vista.resultado.setText(modelo.vender(producto, numero));
            } catch (NumberFormatException error) {
                vista.resultado.setText("Ingrese una cantidad entera valida.");
            } finally {
                vista.nombre.setText("");
                vista.cantidad.setText("");
            }
        });

        // Consulta el inventario y muestra las existencias de todos los productos.
        vista.inventario.addActionListener(e -> {
            vista.resultado.setText(modelo.verInventario());
        });

        // Consulta y muestra los productos que tienen un nivel de stock crítico.
        vista.critico.addActionListener(e -> {
            vista.resultado.setText(modelo.verStockCritico());
        });
    }
}
