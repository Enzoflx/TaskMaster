package POJOS;

import java.time.LocalDateTime;

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

    // Constructor con todos los atrb. (Comprueba que fechaLimite > fechaCreacion)
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

    // Getters y setters para establecer y recuperar los atributos.
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    public LocalDateTime getFechaLimite() { return fechaLimite; }
    public void setFechaLimite(LocalDateTime fechaLimite) { this.fechaLimite = fechaLimite; }
    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    // Metodo toString para mostrar las tareas como texto formateado.
    @Override
    public String toString() {
        return "Tarea " + id + ": " + titulo + " [Estado ID: " + estado + "]";
    }
}