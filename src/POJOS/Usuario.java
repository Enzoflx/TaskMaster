package POJOS;

/**
 * Representa un usuario registrado en el sistema TasksMaster.
 * Contiene información de perfil y credenciales para el inicio de sesión.
 * 
 * @author Enzo Berzosa
 * @version 1.0
 */
public class Usuario {
    private int id;
    private String nombre;
    private String email;
    private String password;

    /**
     * Constructor para crear un usuario nuevo sin identificador.
     * Útil al realizar registros de usuarios nuevos antes de insertarlos en la base de datos.
     * 
     * @param nombre El nombre del usuario.
     * @param email La dirección de correo electrónico del usuario.
     * @param password La contraseña (normalmente encriptada) del usuario.
     */
    public Usuario(String nombre, String email, String password) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

    /**
     * Constructor para reconstruir un usuario existente que ya posee un identificador.
     * Útil al recuperar usuarios de la base de datos.
     * 
     * @param id El identificador único del usuario.
     * @param nombre El nombre del usuario.
     * @param email La dirección de correo electrónico del usuario.
     * @param password La contraseña del usuario.
     */
    public Usuario(int id, String nombre, String email, String password) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

    /**
     * Obtiene el identificador único del usuario.
     * 
     * @return El ID del usuario.
     */
    public int getId() { return id; }

    /**
     * Establece el identificador único del usuario.
     * 
     * @param id El nuevo ID del usuario.
     */
    public void setId(int id) { this.id = id; }

    /**
     * Obtiene el nombre del usuario.
     * 
     * @return El nombre del usuario.
     */
    public String getNombre() { return nombre; }

    /**
     * Establece el nombre del usuario.
     * 
     * @param nombre El nuevo nombre del usuario.
     */
    public void setNombre(String nombre) { this.nombre = nombre; }

    /**
     * Obtiene el correo electrónico del usuario.
     * 
     * @return El email del usuario.
     */
    public String getEmail() { return email; }

    /**
     * Establece el correo electrónico del usuario.
     * 
     * @param email El nuevo email del usuario.
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * Obtiene la contraseña del usuario.
     * 
     * @return La contraseña del usuario.
     */
    public String getPassword() { return password; }

    /**
     * Establece la contraseña del usuario.
     * 
     * @param password La nueva contraseña del usuario.
     */
    public void setPassword(String password) { this.password = password;}

    /**
     * Devuelve una representación legible en texto de la información básica del usuario.
     * 
     * @return Cadena con el nombre y email del usuario.
     */
    @Override
    public String toString() {
        return "Usuario: " + nombre + " (" + email + ")";
    }
}
