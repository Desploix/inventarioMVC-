package Modelo;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ModeloInventario {

    private final Map<String, Integer> productos = new LinkedHashMap<>();

    public void registrarProducto(String nombre, int cantidad) {
        productos.merge(nombre, cantidad, Integer::sum);
    }

    public Map<String, Integer> getProductos() {
        return Collections.unmodifiableMap(productos);
    }
}
