package Modelo;

import java.util.ArrayList;

public class InventarioModelo {

    private ArrayList<String> nombres = new ArrayList<>();
    private ArrayList<Integer> cantidades = new ArrayList<>();

    public String registrar(String producto, int numero) {
        if (producto.isBlank() || numero <= 0) {
            return "Ingrese un nombre y una cantidad mayor que cero.";
        }

        int posicion = -1;
        for (int i = 0; i < nombres.size(); i++) {
            if (nombres.get(i).equals(producto)) {
                posicion = i;
            }
        }

        if (posicion == -1) {
            nombres.add(producto);
            cantidades.add(numero);
        } else {
            cantidades.set(posicion, cantidades.get(posicion) + numero);
        }
        return "Producto registrado o repuesto.";
    }

    public String vender(String producto, int numero) {
        if (producto.isBlank() || numero <= 0) {
            return "Ingrese un nombre y una cantidad mayor que cero.";
        }

        int posicion = -1;
        for (int i = 0; i < nombres.size(); i++) {
            if (nombres.get(i).equals(producto)) {
                posicion = i;
            }
        }

        if (posicion == -1) {
            return "El producto no existe.";
        } else if (numero > cantidades.get(posicion)) {
            return "Stock insuficiente.";
        } else {
            cantidades.set(posicion, cantidades.get(posicion) - numero);
            return "Venta realizada.";
        }
    }

    public String verInventario() {
        String texto = "INVENTARIO\n";
        for (int i = 0; i < nombres.size(); i++) {
            texto = texto + nombres.get(i) + ": " + cantidades.get(i) + "\n";
        }
        return texto;
    }

    public String verStockCritico() {
        String texto = "STOCK CRITICO\n";
        for (int i = 0; i < nombres.size(); i++) {
            if (cantidades.get(i) <= 5) {
                texto = texto + nombres.get(i) + ": " + cantidades.get(i) + "\n";
            }
        }
        return texto;
    }
}
