package DAOS;

import POJOS.Usuario;
import Utilidades.DBUtils;
import java.sql.*;

public class UsuarioDAO {

    // Registra un usuario en la Base de Datos.
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

    // Login de un usuario.
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

    // Mostrar todos los usuarios.
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

    // Muestra el usuario que coincide con una ID concreta.
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
