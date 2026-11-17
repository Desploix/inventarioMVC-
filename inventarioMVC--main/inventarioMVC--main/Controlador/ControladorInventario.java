package Controlador;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

public class ControladorInventario {
    private Vista.InventarioVista vista;
    private Modelo.ModeloInventario modelo;

    public ControladorInventario(Vista.InventarioVista vista, Modelo.ModeloInventario modelo) {
        this.vista = vista;
        this.modelo = modelo;

        this.vista.setRegistrarListener(e -> registrarProducto());
        this.vista.setVenderListener(e -> venderProducto());
        this.vista.setInventarioListener(e -> verInventario());
        this.vista.setCriticoListener(e -> verStockCritico());
    }

    private void registrarProducto() {
        String nombre = vista.getNombreProducto().trim();
        int cantidad = vista.getCantidadProducto();

        if (nombre.isEmpty()) {
            vista.mostrarError("Escribe el nombre del producto.");
            return;
        }
        if (cantidad <= 0) {
            vista.mostrarError("La cantidad debe ser un número entero mayor que cero.");
            return;
        }

        modelo.registrarProducto(nombre, cantidad);
        vista.actualizarTabla(modelo.getProductos());
        vista.limpiarCampos();
    }

    private void venderProducto() {
        String nombre = vista.getNombreProducto().trim();
        int cantidad = vista.getCantidadProducto();

        if (nombre.isEmpty()) {
            vista.mostrarError("Escribe el nombre del producto.");
            return;
        }
        if (cantidad <= 0) {
            vista.mostrarError("La cantidad debe ser un número entero mayor que cero.");
            return;
        }

        try {
            Object productos = modelo.getProductos();
            if (productos == null) {
                vista.mostrarError("No hay suficiente stock del producto.");
                return;
            }

            for (Object producto : (Iterable<?>) productos) {
                if (producto == null) {
                    continue;
                }

                Object nombreActual = invocarGetter(producto, "getNombre");
                if (nombreActual != null && nombreActual.toString().equalsIgnoreCase(nombre)) {
                    int stockActual = obtenerEntero(invocarGetter(producto, "getCantidad"));

                    if (stockActual < cantidad) {
                        vista.mostrarError("No hay suficiente stock del producto.");
                        return;
                    }

                    invocarSetter(producto, "setCantidad", stockActual - cantidad);
                    vista.actualizarTabla(modelo.getProductos());
                    vista.limpiarCampos();
                    return;
                }
            }

            vista.mostrarError("No hay suficiente stock del producto.");
        } catch (Exception e) {
            vista.mostrarError("No hay suficiente stock del producto.");
        }
    }

    private Object invocarGetter(Object objeto, String nombreMetodo) {
        try {
            return objeto.getClass().getMethod(nombreMetodo).invoke(objeto);
        } catch (Exception e) {
            return null;
        }
    }

    private void invocarSetter(Object objeto, String nombreMetodo, int valor) {
        try {
            objeto.getClass().getMethod(nombreMetodo, int.class).invoke(objeto, valor);
        } catch (Exception e) {
            try {
                objeto.getClass().getMethod(nombreMetodo, Integer.class).invoke(objeto, valor);
            } catch (Exception ignored) {
            }
        }
    }

    private int obtenerEntero(Object valor) {
        try {
            return Integer.parseInt(String.valueOf(valor));
        } catch (Exception e) {
            return 0;
        }
    }

    private void verInventario() {
        vista.actualizarTabla(modelo.getProductos());
    }

    @SuppressWarnings("unchecked")
    private Map<String, Integer> obtenerProductosCriticos() {
        try {
            Method metodo = modelo.getClass().getMethod("getProductosCriticos");
            Object resultado = metodo.invoke(modelo);
            return resultado instanceof Map<?, ?>
                    ? (Map<String, Integer>) resultado
                    : new LinkedHashMap<>();
        } catch (NoSuchMethodException e) {
            Object productos = modelo.getProductos();
            Map<String, Integer> criticos = new LinkedHashMap<>();
            if (productos instanceof Map<?, ?>) {
                for (Map.Entry<?, ?> entrada : ((Map<?, ?>) productos).entrySet()) {
                    int cantidad = obtenerEntero(entrada.getValue());
                    if (entrada.getKey() != null && cantidad <= 5) {
                        criticos.put(entrada.getKey().toString(), cantidad);
                    }
                }
            }
            return criticos;
        } catch (Exception e) {
            return new LinkedHashMap<>();
        }
    }

    private void verStockCritico() {
        vista.actualizarTabla(obtenerProductosCriticos());
    }
}