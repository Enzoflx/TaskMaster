import POJOS.Tarea;
import junit.framework.TestCase;
import java.time.LocalDateTime;

/**
 * Pruebas unitarias para la clase Tarea utilizando JUnit 3 en el paquete por defecto.
 * 
 * @author Enzo Berzosa
 * @version 1.0
 */
public class TareaTest extends TestCase {

    /**
     * Prueba que se puede crear una tarea válida.
     */
    public void testCrearTareaValida() {
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime limite = ahora.plusDays(1);
        
        Tarea tarea = new Tarea(1, "Test", "Desc", ahora, limite, null, null, null, null);

        assertNotNull(tarea);
        assertEquals(ahora, tarea.getFechaCreacion());
        assertEquals(limite, tarea.getFechaLimite());
    }

    /**
     * Prueba que la creación lanza excepción con fecha límite inválida.
     */
    public void testCrearTareaConFechaLimiteAnteriorCreacion() {
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime limite = ahora.minusDays(1);

        try {
            new Tarea(1, "Test", "Desc", ahora, limite, null, null, null, null);
            fail("Debería lanzar IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Error: La fecha límite no puede ser anterior a la creación.", e.getMessage());
        }
    }

    /**
     * Prueba el setter de fecha límite con valor válido.
     */
    public void testSetFechaLimiteValida() {
        LocalDateTime ahora = LocalDateTime.now();
        Tarea tarea = new Tarea(1, "Test", "Desc", ahora, null, null, null, null, null);
        
        LocalDateTime limite = ahora.plusDays(1);
        tarea.setFechaLimite(limite);
        assertEquals(limite, tarea.getFechaLimite());
    }

    /**
     * Prueba el setter de fecha límite lanza excepción con valor inválido.
     */
    public void testSetFechaLimiteAnteriorCreacion() {
        LocalDateTime ahora = LocalDateTime.now();
        Tarea tarea = new Tarea(1, "Test", "Desc", ahora, null, null, null, null, null);
        
        try {
            tarea.setFechaLimite(ahora.minusDays(1));
            fail("Debería lanzar IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Error: La fecha límite no puede ser anterior a la creación.", e.getMessage());
        }
    }
}
