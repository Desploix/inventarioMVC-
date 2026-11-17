package Modelo;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Administra los productos y las cantidades disponibles en el inventario.
 */
public class InventarioModelo {

    /** Nombres mostrados para los productos registrados. */
    private ArrayList<String> nombres = new ArrayList<>();

    /** Cantidades disponibles, alineadas por posición con {nombres}. */
    private ArrayList<Integer> cantidades = new ArrayList<>();

    /**
     * Registra un producto nuevo o suma unidades a uno ya existente.
     *
     * Parametro producto, nombre del producto que se va a registrar
     * Parametro numero, cantidad de unidades que se agregan
     * Retorno mensaje con el resultado del registro
     */
    public String registrar(String producto, int numero) {
        if (producto.isBlank() || numero <= 0) {
            return "Ingrese un nombre y una cantidad mayor que cero.";
        }

        int posicion = buscarProducto(producto);

        if (posicion == -1) {
            nombres.add(producto.trim());
            cantidades.add(numero);
        } else {
            cantidades.set(posicion, cantidades.get(posicion) + numero);
        }
        return "Producto registrado o repuesto.";
    }

    /**
     * Descuenta del inventario las unidades vendidas, si hay stock suficiente.
     *
     * Parametro producto, nombre del producto que se va a vender
     * Parametro numero, cantidad de unidades que se venden
     * Retorno mensaje con el resultado de la venta
     */
    public String vender(String producto, int numero) {
        if (producto.isBlank() || numero <= 0) {
            return "Ingrese un nombre y una cantidad mayor que cero.";
        }

        int posicion = buscarProducto(producto);

        if (posicion == -1) {
            return "El producto no existe.";
        } else if (numero > cantidades.get(posicion)) {
            return "Stock insuficiente.";
        } else {
            cantidades.set(posicion, cantidades.get(posicion) - numero);
            return "Venta realizada.";
        }
    }

    /**
     * Busca un producto ignorando diferencias de mayúsculas y espacios.
     *
     * Parametro producto, nombre del producto que se busca
     * Retorno posición del producto o {-1} si no existe
     */
    private int buscarProducto(String producto) {
        String productoNormalizado = normalizarProducto(producto);
        for (int i = 0; i < nombres.size(); i++) {
            if (normalizarProducto(nombres.get(i)).equals(productoNormalizado)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Prepara un nombre para compararlo sin distinguir espacios ni mayúsculas.
     *
     * Parametro producto, nombre que se normaliza
     * Retorno nombre sin espacios y en minúsculas
     */
    private String normalizarProducto(String producto) {
        return producto.replaceAll("\\s+", "").toLowerCase(Locale.ROOT);
    }

    /**
     * Genera un listado de todos los productos y sus existencias.
     *
     * Retorno texto con el inventario completo
     */
    public String verInventario() {
        String texto = "INVENTARIO\n";
        for (int i = 0; i < nombres.size(); i++) {
            texto = texto + nombres.get(i) + ": " + cantidades.get(i) + "\n";
        }
        return texto;
    }

    /**
     * Genera un listado de productos con cinco unidades o menos.
     *
     * Retorno texto con los productos cuyo stock es crítico
     */
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
