import POJOS.Usuario;
import junit.framework.TestCase;

/**
 * Pruebas unitarias para la clase Usuario utilizando JUnit 3 en el paquete por defecto.
 * 
 * @author Enzo Berzosa
 * @version 1.0
 */
public class UsuarioTest extends TestCase {

    /**
     * Prueba el constructor sin ID y los getters/setters.
     */
    public void testConstructorSinIdYGettersSetters() {
        Usuario usr = new Usuario("Enzo", "enzo@email.com", "12345678");
        
        assertEquals(0, usr.getId()); // Por defecto
        assertEquals("Enzo", usr.getNombre());
        assertEquals("enzo@email.com", usr.getEmail());
        assertEquals("12345678", usr.getPassword());

        usr.setId(5);
        usr.setNombre("Enzo Berzosa");
        usr.setEmail("enzo.berzosa@email.com");
        usr.setPassword("12345678");

        assertEquals(5, usr.getId());
        assertEquals("Enzo Berzosa", usr.getNombre());
        assertEquals("enzo.berzosa@email.com", usr.getEmail());
        assertEquals("12345678", usr.getPassword());
    }

    /**
     * Prueba el constructor con ID.
     */
    public void testConstructorConId() {
        Usuario usr = new Usuario(10, "Carlos", "carlos@email.com", "12345678");
        
        assertEquals(10, usr.getId());
        assertEquals("Carlos", usr.getNombre());
        assertEquals("carlos@email.com", usr.getEmail());
        assertEquals("12345678", usr.getPassword());
    }

    /**
     * Prueba el método toString de Usuario.
     */
    public void testToString() {
        Usuario usr = new Usuario("Enzo", "enzo@email.com", "12345678");
        assertEquals("Usuario: Enzo (enzo@email.com)", usr.toString());
    }
}
