import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

// Suite de pruebas completa
@Suite
@SelectClasses({ProductoTest.class, ItemCarritoTest.class, CarritoTest.class})
public class CarritoComprasTestSuite {
    // Esta clase ejecuta todas las pruebas juntas
}