import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.pucmm.practica_1.Carrito;
import org.pucmm.practica_1.ItemCarrito;
import org.pucmm.practica_1.Producto;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class CarritoTest {

    private Carrito carrito;
    private Producto producto1;
    private Producto producto2;
    private Producto producto3;

    @BeforeEach
    void setUp() {
        carrito = new Carrito();
        producto1 = new Producto("Laptop", 1000.0, "Laptop Gaming");
        producto2 = new Producto("Mouse", 25.0, "Mouse óptico");
        producto3 = new Producto("Teclado", 75.0, "Teclado mecánico");
    }

    @Test
    @DisplayName("Crear carrito vacío")
    void testCrearCarritoVacio() {
        assertNotNull(carrito);
        assertTrue(carrito.estaVacio());
        assertEquals(0, carrito.calcularTotal());
        assertEquals(0, carrito.getCantidadTotalProductos());
    }

    @Test
    @DisplayName("Agregar producto al carrito")
    void testAgregarProducto() {
        carrito.agregarProducto(producto1, 1);
        assertFalse(carrito.estaVacio());
        assertTrue(carrito.contieneProducto(producto1));
        assertEquals(1, carrito.getCantidadProducto(producto1));
        assertEquals(1000.0, carrito.calcularTotal());
    }

    @Test
    @DisplayName("No permitir agregar producto nulo")
    void testAgregarProductoNulo() {
        assertThrows(IllegalArgumentException.class, () -> carrito.agregarProducto(null, 1));
    }

    @Test
    @DisplayName("No permitir agregar cantidad negativa")
    void testAgregarCantidadNegativa() {
        assertThrows(IllegalArgumentException.class, () -> carrito.agregarProducto(producto1, -1));
    }

    @Test
    @DisplayName("Agregar mismo producto incrementa cantidad")
    void testAgregarMismoProducto() {
        carrito.agregarProducto(producto1, 1);
        carrito.agregarProducto(producto1, 2);
        assertEquals(3, carrito.getCantidadProducto(producto1));
        assertEquals(3000.0, carrito.calcularTotal());
    }

    @Test
    @DisplayName("Agregar múltiples productos diferentes")
    void testAgregarMultiplesProductos() {
        carrito.agregarProducto(producto1, 1);
        carrito.agregarProducto(producto2, 2);
        carrito.agregarProducto(producto3, 1);

        assertEquals(3, carrito.getItems().size());
        assertEquals(1, carrito.getCantidadProducto(producto1));
        assertEquals(2, carrito.getCantidadProducto(producto2));
        assertEquals(1, carrito.getCantidadProducto(producto3));
        assertEquals(1125.0, carrito.calcularTotal()); // 1000 + 50 + 75
    }

    @Test
    @DisplayName("Eliminar producto del carrito")
    void testEliminarProducto() {
        carrito.agregarProducto(producto1, 1);
        carrito.agregarProducto(producto2, 2);

        assertTrue(carrito.eliminarProducto(producto1));
        assertFalse(carrito.contieneProducto(producto1));
        assertTrue(carrito.contieneProducto(producto2));
        assertEquals(50.0, carrito.calcularTotal());
    }

    @Test
    @DisplayName("Eliminar producto inexistente")
    void testEliminarProductoInexistente() {
        carrito.agregarProducto(producto1, 1);
        assertFalse(carrito.eliminarProducto(producto2));
        assertTrue(carrito.contieneProducto(producto1));
    }

    @Test
    @DisplayName("No permitir eliminar producto nulo")
    void testEliminarProductoNulo() {
        assertThrows(IllegalArgumentException.class, () -> carrito.eliminarProducto(null));
    }

    @Test
    @DisplayName("Modificar cantidad de producto")
    void testModificarCantidad() {
        carrito.agregarProducto(producto1, 1);
        assertTrue(carrito.modificarCantidad(producto1, 3));
        assertEquals(3, carrito.getCantidadProducto(producto1));
        assertEquals(3000.0, carrito.calcularTotal());
    }

    @Test
    @DisplayName("Modificar cantidad de producto inexistente")
    void testModificarCantidadInexistente() {
        carrito.agregarProducto(producto1, 1);
        assertFalse(carrito.modificarCantidad(producto2, 3));
    }

    @Test
    @DisplayName("No permitir modificar cantidad a cero")
    void testModificarCantidadCero() {
        carrito.agregarProducto(producto1, 1);
        assertThrows(IllegalArgumentException.class, () -> carrito.modificarCantidad(producto1, 0));
    }

    @Test
    @DisplayName("No permitir modificar cantidad de producto nulo")
    void testModificarCantidadProductoNulo() {
        assertThrows(IllegalArgumentException.class, () -> carrito.modificarCantidad(null, 1));
    }

    @Test
    @DisplayName("Calcular total con múltiples productos")
    void testCalcularTotal() {
        carrito.agregarProducto(producto1, 2); // 2000
        carrito.agregarProducto(producto2, 3); // 75
        carrito.agregarProducto(producto3, 1); // 75
        assertEquals(2150.0, carrito.calcularTotal());
    }

    @Test
    @DisplayName("Obtener cantidad total de productos")
    void testGetCantidadTotalProductos() {
        carrito.agregarProducto(producto1, 2);
        carrito.agregarProducto(producto2, 3);
        carrito.agregarProducto(producto3, 1);
        assertEquals(6, carrito.getCantidadTotalProductos());
    }

    @Test
    @DisplayName("Vaciar carrito")
    void testVaciarCarrito() {
        carrito.agregarProducto(producto1, 1);
        carrito.agregarProducto(producto2, 2);

        carrito.vaciarCarrito();
        assertTrue(carrito.estaVacio());
        assertEquals(0, carrito.calcularTotal());
        assertEquals(0, carrito.getCantidadTotalProductos());
    }

    @Test
    @DisplayName("Obtener lista de items (copia defensiva)")
    void testGetItems() {
        carrito.agregarProducto(producto1, 1);
        List<ItemCarrito> items = carrito.getItems();
        assertEquals(1, items.size());

        // Modificar la lista devuelta no debe afectar el carrito original
        items.clear();
        assertFalse(carrito.estaVacio());
    }

    @Test
    @DisplayName("Obtener cantidad de producto inexistente")
    void testGetCantidadProductoInexistente() {
        carrito.agregarProducto(producto1, 1);
        assertEquals(0, carrito.getCantidadProducto(producto2));
    }

    @Test
    @DisplayName("Verificar toString del carrito")
    void testToString() {
        carrito.agregarProducto(producto1, 1);
        String carritoString = carrito.toString();
        assertTrue(carritoString.contains("Carrito de Compras"));
        assertTrue(carritoString.contains("Laptop"));
        assertTrue(carritoString.contains("Total: $1000.00"));
    }

    @Test
    @DisplayName("ToString de carrito vacío")
    void testToStringCarritoVacio() {
        String carritoString = carrito.toString();
        assertTrue(carritoString.contains("(Vacío)"));
    }
}
