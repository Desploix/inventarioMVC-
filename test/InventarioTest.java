package test;

import Modelo.InventarioModelo;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InventarioTest {

    private InventarioModelo inventario;
    
    // Prepara un inventario nuevo antes de ejecutar cada prueba, evitando que
    // los productos registrados en una prueba afecten a las demás.
    @Before
    public void setUp() {
        inventario = new InventarioModelo();
    }

    // Comprueba que se pueda registrar un producto nuevo y que el método
    // devuelva el mensaje esperado.
    @Test
    public void testRegistrarProductoNuevo() {
        String resultado = inventario.registrar("Producto A", 10);
        assertEquals("Producto registrado o repuesto.", resultado);
    }

    // Comprueba que registrar un producto que ya existe también devuelva
    // el mensaje de registro o reposición esperado.
    @Test
    public void testRegistrarProductoExistente() {
        inventario.registrar("Producto A", 10);
        String resultado = inventario.registrar("Producto A", 5);
        assertEquals("Producto registrado o repuesto.", resultado);
    }

    // Comprueba que se pueda vender un producto existente cuando hay
    // suficiente stock para completar la venta.
    @Test
    public void testVenderProductoExistenteConStockSuficiente() {
        inventario.registrar("Producto A", 10);
        String resultado = inventario.vender("Producto A", 5);
        assertEquals("Venta realizada.", resultado);
    }

    // Comprueba que la venta se rechace cuando se solicita más cantidad
    // de la disponible en el inventario.
    @Test
    public void testVenderProductoExistenteConStockInsuficiente() {
        inventario.registrar("Producto A", 10);
        String resultado = inventario.vender("Producto A", 15);
        assertEquals("Stock insuficiente.", resultado);
    }

    // Comprueba que no se pueda vender un producto que no se ha registrado.
    @Test
    public void testVenderProductoInexistente() {
        String resultado = inventario.vender("Producto B", 5);
        assertEquals("El producto no existe.", resultado);
    }
	
}