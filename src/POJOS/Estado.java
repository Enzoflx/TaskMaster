package POJOS;

public class Estado {
    private int id;
    private String nombre;

    // Constructor único con todos los atributos.
    public Estado(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters y setters para establecer y recuperar los atributos.
    public int getId() { return id; }
    public void setId(int id) { this.id = id;}
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre;}
}