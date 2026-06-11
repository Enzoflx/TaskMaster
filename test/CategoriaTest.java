import POJOS.Categoria;
import junit.framework.TestCase;

/**
 * Pruebas unitarias para la clase Categoria utilizando JUnit 3 en el paquete por defecto.
 * 
 * @author Enzo Berzosa
 * @version 1.0
 */
public class CategoriaTest extends TestCase {

    /**
     * Prueba los constructores, getters y setters de Categoria.
     */
    public void testGettersYSetters() {
        Categoria cat = new Categoria(1, "Trabajo", "Tareas de oficina");
        
        assertEquals(1, cat.getId());
        assertEquals("Trabajo", cat.getNombre());
        assertEquals("Tareas de oficina", cat.getDescripcion());

        cat.setId(2);
        cat.setNombre("Hogar");
        cat.setDescripcion("Tareas domésticas");

        assertEquals(2, cat.getId());
        assertEquals("Hogar", cat.getNombre());
        assertEquals("Tareas domésticas", cat.getDescripcion());
    }

    /**
     * Prueba el método toString de Categoria.
     */
    public void testToString() {
        Categoria cat = new Categoria(1, "Trabajo", "Tareas de oficina");
        assertEquals("Trabajo", cat.toString());
    }
}
