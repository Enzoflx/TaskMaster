package POJOS;

/**
 * Representa el estado en el que se encuentra una tarea en TasksMaster (por ejemplo, Pendiente, Completada).
 * 
 * @author Enzo Berzosa
 * @version 1.0
 */
public class Estado {
    private int id;
    private String nombre;

    /**
     * Crea una instancia de Estado con todos sus atributos.
     * 
     * @param id El identificador único del estado.
     * @param nombre El nombre único del estado.
     */
    public Estado(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    /**
     * Obtiene el identificador único del estado.
     * 
     * @return El ID del estado.
     */
    public int getId() { return id; }

    /**
     * Establece el identificador único del estado.
     * 
     * @param id El nuevo ID del estado.
     */
    public void setId(int id) { this.id = id;}

    /**
     * Obtiene el nombre descriptivo del estado.
     * 
     * @return El nombre del estado.
     */
    public String getNombre() { return nombre; }

    /**
     * Establece el nombre descriptivo del estado.
     * 
     * @param nombre El nuevo nombre del estado.
     */
    public void setNombre(String nombre) { this.nombre = nombre;}

    /**
     * Devuelve una representación legible en texto de este estado, correspondiente a su nombre.
     * 
     * @return El nombre del estado.
     */
    @Override
    public String toString() {
        return nombre;
    }
}
