import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.pucmm.practica_1.ItemCarrito;
import org.pucmm.practica_1.Producto;

import static org.junit.jupiter.api.Assertions.*;

public class ItemCarritoTest {

    private Producto producto;
    private ItemCarrito item;

    @BeforeEach
    void setUp() {
        producto = new Producto("Mouse", 25.0, "Mouse óptico");
        item = new ItemCarrito(producto, 2);
    }

    @Test
    @DisplayName("Crear item con datos válidos")
    void testCrearItemValido() {
        assertNotNull(item);
        assertEquals(producto, item.getProducto());
        assertEquals(2, item.getCantidad());
        assertEquals(50.0, item.getSubtotal());
    }

    @Test
    @DisplayName("No permitir crear item con producto nulo")
    void testCrearItemProductoNulo() {
        assertThrows(IllegalArgumentException.class, () -> new ItemCarrito(null, 1));
    }

    @Test
    @DisplayName("No permitir crear item con cantidad negativa")
    void testCrearItemCantidadNegativa() {
        assertThrows(IllegalArgumentException.class, () -> new ItemCarrito(producto, -1));
    }

    @Test
    @DisplayName("No permitir crear item con cantidad cero")
    void testCrearItemCantidadCero() {
        assertThrows(IllegalArgumentException.class, () -> new ItemCarrito(producto, 0));
    }

    @Test
    @DisplayName("Calcular subtotal correctamente")
    void testCalcularSubtotal() {
        assertEquals(50.0, item.getSubtotal());
        item.setCantidad(3);
        assertEquals(75.0, item.getSubtotal());
    }

    @Test
    @DisplayName("Modificar cantidad del item")
    void testSetCantidad() {
        item.setCantidad(5);
        assertEquals(5, item.getCantidad());
        assertEquals(125.0, item.getSubtotal());
    }

    @Test
    @DisplayName("No permitir modificar cantidad a cero")
    void testSetCantidadCero() {
        assertThrows(IllegalArgumentException.class, () -> item.setCantidad(0));
    }

    @Test
    @DisplayName("Incrementar cantidad")
    void testIncrementarCantidad() {
        item.incrementarCantidad(3);
        assertEquals(5, item.getCantidad());
    }

    @Test
    @DisplayName("No permitir incremento negativo")
    void testIncrementarCantidadNegativa() {
        assertThrows(IllegalArgumentException.class, () -> item.incrementarCantidad(-1));
    }

    @Test
    @DisplayName("Decrementar cantidad")
    void testDecrementarCantidad() {
        ItemCarrito itemGrande = new ItemCarrito(producto, 5);
        itemGrande.decrementarCantidad(2);
        assertEquals(3, itemGrande.getCantidad());
    }

    @Test
    @DisplayName("No permitir decrementar más de la cantidad actual")
    void testDecrementarCantidadExcesiva() {
        assertThrows(IllegalArgumentException.class, () -> {
            item.decrementarCantidad(3); // item tiene 2, no puede decrementar 3
        });
    }

    @Test
    @DisplayName("No permitir decrementar cantidad igual a la actual")
    void testDecrementarCantidadIgual() {
        assertThrows(IllegalArgumentException.class, () -> {
            item.decrementarCantidad(2); // item tiene 2, no puede decrementar 2
        });
    }

    @Test
    @DisplayName("Probar equals de items")
    void testEquals() {
        ItemCarrito item2 = new ItemCarrito(producto, 2);
        assertEquals(item, item2);
    }
}