package POJOS;

import java.time.LocalDateTime;

/**
 * Representa una tarea dentro del sistema TasksMaster.
 * Contiene información sobre el título, descripción, fechas, estado, usuario propietario,
 * categoría y observaciones asociadas.
 * 
 * @author Enzo Berzosa
 * @version 1.0
 */
public class Tarea {
    private int id;
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaLimite;
    private Estado estado;
    private Usuario usuario;
    private Categoria categoria;
    private String observaciones;

    /**
     * Crea una instancia de Tarea con todos sus atributos.
     * Valida que la fecha límite no sea anterior a la fecha de creación.
     * 
     * @param id El identificador único de la tarea.
     * @param titulo El título o nombre de la tarea.
     * @param descripcion La descripción detallada de la tarea.
     * @param fechaCreacion La fecha y hora en que se creó la tarea.
     * @param fechaLimite La fecha y hora límite de finalización para la tarea (puede ser null).
     * @param estado El estado actual en el que se encuentra la tarea.
     * @param usuario El usuario propietario o asignado a la tarea.
     * @param categoria La categoría a la que pertenece la tarea.
     * @param observaciones Notas u observaciones adicionales sobre la tarea.
     * @throws IllegalArgumentException Si la fecha límite no es nula y es anterior a la fecha de creación.
     */
    public Tarea(int id, String titulo, String descripcion, LocalDateTime fechaCreacion, LocalDateTime fechaLimite, Estado estado, Usuario usuario, Categoria categoria, String observaciones) {
        if (fechaLimite != null && fechaLimite.isBefore(fechaCreacion)) {
            throw new IllegalArgumentException("Error: La fecha límite no puede ser anterior a la creación.");
        }

        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
        this.fechaLimite = fechaLimite;
        this.estado = estado;
        this.usuario = usuario;
        this.categoria = categoria;
        this.observaciones = observaciones;
    }

    /**
     * Obtiene el identificador único de la tarea.
     * 
     * @return El ID de la tarea.
     */
    public int getId() { return id; }

    /**
     * Establece el identificador único de la tarea.
     * 
     * @param id El nuevo ID de la tarea.
     */
    public void setId(int id) { this.id = id; }

    /**
     * Obtiene el título de la tarea.
     * 
     * @return El título de la tarea.
     */
    public String getTitulo() { return titulo; }

    /**
     * Establece el título de la tarea.
     * 
     * @param titulo El nuevo título de la tarea.
     */
    public void setTitulo(String titulo) { this.titulo = titulo; }

    /**
     * Obtiene la descripción detallada de la tarea.
     * 
     * @return La descripción de la tarea.
     */
    public String getDescripcion() { return descripcion; }

    /**
     * Establece la descripción detallada de la tarea.
     * 
     * @param descripcion La nueva descripción de la tarea.
     */
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    /**
     * Obtiene la fecha y hora de creación de la tarea.
     * 
     * @return La fecha de creación de la tarea.
     */
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }

    /**
     * Establece la fecha y hora de creación de la tarea.
     * 
     * @param fechaCreacion La nueva fecha de creación de la tarea.
     */
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    /**
     * Obtiene la fecha y hora límite de la tarea.
     * 
     * @return La fecha límite de la tarea (puede ser null).
     */
    public LocalDateTime getFechaLimite() { return fechaLimite; }

    /**
     * Establece la fecha y hora límite de la tarea.
     * Valida que la nueva fecha límite no sea anterior a la fecha de creación de la tarea.
     * 
     * @param fechaLimite La nueva fecha límite de la tarea.
     * @throws IllegalArgumentException Si la fecha límite no es nula y es anterior a la fecha de creación.
     */
    public void setFechaLimite(LocalDateTime fechaLimite) {
        if (fechaLimite != null && this.fechaCreacion != null && fechaLimite.isBefore(this.fechaCreacion)) {
            throw new IllegalArgumentException("Error: La fecha límite no puede ser anterior a la creación.");
        }
        this.fechaLimite = fechaLimite;
    }

    /**
     * Obtiene el estado actual de la tarea.
     * 
     * @return El estado de la tarea.
     */
    public Estado getEstado() { return estado; }

    /**
     * Establece el estado actual de la tarea.
     * 
     * @param estado El nuevo estado de la tarea.
     */
    public void setEstado(Estado estado) { this.estado = estado; }

    /**
     * Obtiene el usuario propietario de la tarea.
     * 
     * @return El usuario propietario.
     */
    public Usuario getUsuario() { return usuario; }

    /**
     * Establece el usuario propietario de la tarea.
     * 
     * @param usuario El nuevo usuario propietario.
     */
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    /**
     * Obtiene la categoría asignada a la tarea.
     * 
     * @return La categoría de la tarea.
     */
    public Categoria getCategoria() { return categoria; }

    /**
     * Establece la categoría asignada a la tarea.
     * 
     * @param categoria La nueva categoría de la tarea.
     */
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    /**
     * Obtiene las observaciones o notas adicionales de la tarea.
     * 
     * @return Las observaciones de la tarea.
     */
    public String getObservaciones() { return observaciones; }

    /**
     * Establece las observaciones o notas adicionales de la tarea.
     * 
     * @param observaciones Las nuevas observaciones de la tarea.
     */
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    /**
     * Devuelve una representación en formato de texto legible de la tarea.
     * 
     * @return Cadena de texto con los datos básicos de la tarea.
     */
    @Override
    public String toString() {
        String fLimite = (fechaLimite != null) ? fechaLimite.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) : "Sin fecha";
        return "Tarea #" + id + " | [" + titulo + "] | Cat: " + categoria + " | Est: " + estado + " | Límite: " + fLimite;
    }
}
