import Modelo.InventarioModelo;
import Vista.InventarioVista;
import Controlador.InventarioControlador;

public class Main {

    public static void main(String[] args) {
        InventarioModelo modelo = new InventarioModelo();
        InventarioVista vista = new InventarioVista();
        new InventarioControlador(vista, modelo);
        vista.setVisible(true);
    }
}
