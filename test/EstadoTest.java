import POJOS.Estado;
import junit.framework.TestCase;

/**
 * Pruebas unitarias para la clase Estado utilizando JUnit 3 en el paquete por defecto.
 * 
 * @author Enzo Berzosa
 * @version 1.0
 */
public class EstadoTest extends TestCase {

    /**
     * Prueba los constructores, getters y setters de Estado.
     */
    public void testGettersYSetters() {
        Estado est = new Estado(1, "Pendiente");
        
        assertEquals(1, est.getId());
        assertEquals("Pendiente", est.getNombre());

        est.setId(2);
        est.setNombre("En Progreso");

        assertEquals(2, est.getId());
        assertEquals("En Progreso", est.getNombre());
    }

    /**
     * Prueba el método toString de Estado.
     */
    public void testToString() {
        Estado est = new Estado(1, "Pendiente");
        assertEquals("Pendiente", est.toString());
    }
}
