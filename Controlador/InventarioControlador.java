package Controlador;

import Modelo.InventarioModelo;
import Vista.InventarioVista;

public class InventarioControlador {

    public InventarioControlador(InventarioVista vista, InventarioModelo modelo) {

        vista.registrar.addActionListener(e -> {
            try {
                String producto = vista.nombre.getText();
                int numero = Integer.parseInt(vista.cantidad.getText());
                vista.resultado.setText(modelo.registrar(producto, numero));
            } catch (NumberFormatException error) {
                vista.resultado.setText("Ingrese una cantidad entera valida.");
            }
        });

        vista.vender.addActionListener(e -> {
            try {
                String producto = vista.nombre.getText();
                int numero = Integer.parseInt(vista.cantidad.getText());
                vista.resultado.setText(modelo.vender(producto, numero));
            } catch (NumberFormatException error) {
                vista.resultado.setText("Ingrese una cantidad entera valida.");
            }
        });

        vista.inventario.addActionListener(e -> {
            vista.resultado.setText(modelo.verInventario());
        });

        vista.critico.addActionListener(e -> {
            vista.resultado.setText(modelo.verStockCritico());
        });
    }
}
