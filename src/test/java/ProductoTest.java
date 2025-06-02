import org.junit.jupiter.api.*;
import org.pucmm.practica_1.Producto;

import static org.junit.jupiter.api.Assertions.*;

public class ProductoTest {

    private Producto producto;

    @BeforeEach
    void setUp() {
        producto = new Producto("Laptop", 1000.0, "Laptop para programación");
    }

    @Test
    @DisplayName("Crear producto con datos válidos")
    void testCrearProductoValido() {
        assertNotNull(producto);
        assertEquals("Laptop", producto.getNombre());
        assertEquals(1000.0, producto.getPrecio());
        assertEquals("Laptop para programación", producto.getDescripcion());
    }

    @Test
    @DisplayName("No permitir crear producto con nombre nulo")
    void testCrearProductoNombreNulo() {
        assertThrows(IllegalArgumentException.class, () -> new Producto(null, 100.0, "Descripción"));
    }

    @Test
    @DisplayName("No permitir crear producto con nombre vacío")
    void testCrearProductoNombreVacio() {
        assertThrows(IllegalArgumentException.class, () -> new Producto("", 100.0, "Descripción"));
    }

    @Test
    @DisplayName("No permitir crear producto con precio negativo")
    void testCrearProductoPrecioNegativo() {
        assertThrows(IllegalArgumentException.class, () -> new Producto("Producto", -10.0, "Descripción"));
    }

    @Test
    @DisplayName("No permitir crear producto con precio cero")
    void testCrearProductoPrecioCero() {
        assertThrows(IllegalArgumentException.class, () -> new Producto("Producto", 0.0, "Descripción"));
    }

    @Test
    @DisplayName("Permitir descripción nula")
    void testCrearProductoDescripcionNula() {
        Producto producto = new Producto("Producto", 100.0, null);
        assertEquals("", producto.getDescripcion());
    }

    @Test
    @DisplayName("Modificar nombre del producto")
    void testSetNombre() {
        producto.setNombre("Mouse");
        assertEquals("Mouse", producto.getNombre());
    }

    @Test
    @DisplayName("No permitir modificar nombre a nulo")
    void testSetNombreNulo() {
        assertThrows(IllegalArgumentException.class, () -> producto.setNombre(null));
    }

    @Test
    @DisplayName("Modificar precio del producto")
    void testSetPrecio() {
        producto.setPrecio(1500.0);
        assertEquals(1500.0, producto.getPrecio());
    }

    @Test
    @DisplayName("No permitir modificar precio a negativo")
    void testSetPrecioNegativo() {
        assertThrows(IllegalArgumentException.class, () -> producto.setPrecio(-100.0));
    }

    @Test
    @DisplayName("Modificar descripción del producto")
    void testSetDescripcion() {
        producto.setDescripcion("Nueva descripción");
        assertEquals("Nueva descripción", producto.getDescripcion());
    }

    @Test
    @DisplayName("Probar equals de productos")
    void testEquals() {
        Producto producto2 = new Producto("Laptop", 1000.0, "Laptop para programación");
        assertEquals(producto, producto2);
    }

    @Test
    @DisplayName("Probar toString del producto")
    void testToString() {
        String expected = "Producto{nombre='Laptop', precio=1000.00, descripcion='Laptop para programación'}";
        assertEquals(expected, producto.toString());
    }
}