package POJOS;

/**
 * Representa una categoría de tareas dentro del sistema TasksMaster.
 * Permite clasificar las tareas (por ejemplo, Trabajo, Hogar).
 * 
 * @author Enzo Berzosa
 * @version 1.0
 */
public class Categoria {
    private int id;
    private String nombre;
    private String descripcion;

    /**
     * Crea una instancia de Categoria con todos sus atributos.
     * 
     * @param id El identificador único de la categoría.
     * @param nombre El nombre único de la categoría.
     * @param descripcion La descripción detallada de la categoría.
     */
    public Categoria(int id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el identificador único de la categoría.
     * 
     * @return El ID de la categoría.
     */
    public int getId() { return id;}

    /**
     * Establece el identificador único de la categoría.
     * 
     * @param id El nuevo ID de la categoría.
     */
    public void setId(int id) {this.id = id;}

    /**
     * Obtiene el nombre de la categoría.
     * 
     * @return El nombre de la categoría.
     */
    public String getNombre() {return nombre;}

    /**
     * Establece el nombre de la categoría.
     * 
     * @param nombre El nuevo nombre de la categoría.
     */
    public void setNombre(String nombre) { this.nombre = nombre;}

    /**
     * Obtiene la descripción de la categoría.
     * 
     * @return La descripción de la categoría.
     */
    public String getDescripcion() {return descripcion;}

    /**
     * Establece la descripción de la categoría.
     * 
     * @param descripcion La nueva descripción de la categoría.
     */
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    /**
     * Devuelve una representación legible en texto de la categoría, correspondiente a su nombre.
     * 
     * @return El nombre de la categoría.
     */
    @Override
    public String toString() {
        return nombre;
    }
}
