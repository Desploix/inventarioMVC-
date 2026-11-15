
import Controlador.ControladorInventario;
import Modelo.ModeloInventario;
import Vista.InventarioVista;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InventarioVista ventana = new InventarioVista();
            ModeloInventario modelo = new ModeloInventario();
            new ControladorInventario(ventana, modelo);
            ventana.setVisible(true);
        });
    }
}
