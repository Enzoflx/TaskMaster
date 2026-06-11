package DAOS;

import POJOS.Usuario;
import Utilidades.DBUtils;
import java.sql.*;

/**
 * Clase de Acceso a Datos (DAO) para realizar operaciones sobre la entidad Usuario.
 * Permite registrar usuarios, realizar login, listar y buscar usuarios en la base de datos.
 * 
 * @author Enzo Berzosa
 * @version 1.0
 */
public class UsuarioDAO {

    /**
     * Constructor por defecto de UsuarioDAO.
     */
    public UsuarioDAO() {
    }

    /**
     * Registra un nuevo usuario en la base de datos.
     * 
     * @param usuario El usuario a registrar.
     * @return true si el registro fue exitoso; false en caso contrario.
     */
    public boolean registro(Usuario usuario) {
        String sql = "INSERT INTO Usuarios (nombre, email, password) VALUES (?, ?, ?)";
        try (Connection con = DBUtils.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
             ps.setString(1, usuario.getNombre());
             ps.setString(2, usuario.getEmail());
             ps.setString(3, usuario.getPassword());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al registrar usuario: " + e.getMessage());
            return false;
        }
    }

    /**
     * Autentica un usuario validando su dirección de correo electrónico y contraseña.
     * 
     * @param email El email del usuario.
     * @param password La contraseña del usuario.
     * @return El objeto Usuario autenticado si las credenciales son correctas; null en caso contrario.
     */
    public Usuario login(String email, String password) {
        String sql = "SELECT * FROM Usuarios WHERE email = ? AND password = ?";
        try (Connection con = DBUtils.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
             ps.setString(1, email);
             ps.setString(2, password);
             try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("email"), rs.getString("password"));
                }
             }
        } catch (SQLException e) {
            System.out.println("Error al realizar login: " + e.getMessage());
        }
        return null;
    }

    /**
     * Muestra en la consola la lista de todos los usuarios registrados (ID, Nombre y Email).
     */
    public void mostrarUsuarios() {
        String sql = "SELECT id, nombre, email FROM Usuarios";
        try (Connection con = DBUtils.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

             System.out.println("\nListado de Usuarios:");
             while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + " | Nombre: " + rs.getString("nombre") + " | Email: " + rs.getString("email"));
             }
        } catch (SQLException e) {
            System.out.println("Error al listar los usuarios: " + e.getMessage());
        }
    }

    /**
     * Obtiene un usuario concreto a partir de su identificador único.
     * 
     * @param id El identificador único del usuario.
     * @return El objeto Usuario si existe; null si no se encuentra o hay error.
     */
    public Usuario obtenerPorId(int id) {
        String sql = "SELECT * FROM Usuarios WHERE id = ?";
        try (Connection con = DBUtils.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
             ps.setInt(1, id);
             try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("email"), rs.getString("password"));
                }
             }
        } catch (SQLException e) {
            System.out.println("Error al obtener usuario: " + e.getMessage());
        }
        return null;
    }
}
