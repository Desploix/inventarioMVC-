import Modelo.InventarioModelo;
import Vista.InventarioVista;
import Controlador.InventarioControlador;

/**
 * Inicia la aplicación de gestión de inventario.
 */
public class Main {

    /**
     * Crea el modelo, la interfaz y el controlador, y muestra la ventana.
     *
     * @param args argumentos de línea de comandos
     */
    public static void main(String[] args) {
        InventarioModelo modelo = new InventarioModelo();
        InventarioVista vista = new InventarioVista();
        new InventarioControlador(vista, modelo);
        vista.setVisible(true);
    }
}
