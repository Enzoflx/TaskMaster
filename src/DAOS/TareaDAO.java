package DAOS;

import POJOS.*;
import Utilidades.DBUtils;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TareaDAO {
    // Necesitamos los otros DAOs para reconstruir los objetos (Hay relación de composición)
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private EstadoDAO estadoDAO = new EstadoDAO();
    private CategoriaDAO categoriaDAO = new CategoriaDAO();

    // Crear una tarea con todos sus atributos. Necesito convertir la fecha a formato MySQL.
    public boolean insertar(Tarea tarea) {
        String sql = "INSERT INTO Tareas (titulo, descripcion, fecha_limite, estado_id, usuario_propietario_id, categoria_id, observaciones) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = DBUtils.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, tarea.getTitulo());
            ps.setString(2, tarea.getDescripcion());
            if (tarea.getFechaLimite() != null) {
                LocalDateTime fecha = tarea.getFechaLimite();
                ps.setTimestamp(3, Timestamp.valueOf(fecha));
            } else {
                ps.setNull(3, java.sql.Types.TIMESTAMP);
            }
            ps.setInt(4, tarea.getEstado().getId());
            ps.setInt(5, tarea.getUsuario().getId());
            ps.setInt(6, tarea.getCategoria().getId());
            ps.setString(7, tarea.getObservaciones());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar tarea: " + e.getMessage());
            return false;
        }
    }

    // 2. Muestra las tareas de un usuario concreto.
    public List<Tarea> listarPorUsuario(int idUsuario) {
        List<Tarea> lista = new ArrayList<>();
        String sql = "SELECT * FROM Tareas WHERE usuario_propietario_id = ?";

        try (Connection con = DBUtils.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Tarea tarea = new Tarea(rs.getInt("id"), rs.getString("titulo"), rs.getString("descripcion"), rs.getTimestamp("fecha_creacion").toLocalDateTime(), rs.getTimestamp("fecha_limite") != null ? rs.getTimestamp("fecha_limite").toLocalDateTime() : null, estadoDAO.obtenerPorId(rs.getInt("estado_id")), usuarioDAO.obtenerPorId(rs.getInt("usuario_propietario_id")), categoriaDAO.obtenerPorId(rs.getInt("categoria_id")), rs.getString("observaciones"));
                    lista.add(tarea);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al listar tareas: " + e.getMessage());
        }
        return lista;
    }

    // 3. Permite eliminar una tarea existente creada.
    public boolean eliminar(int idTarea) {
        String sql = "DELETE FROM Tareas WHERE id = ?";
        try (Connection con = DBUtils.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idTarea);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar tarea: " + e.getMessage());
            return false;
        }
    }

    // 4. Cambia el estado de una tarea al estado Completada.
    public boolean marcarCompletada(int idTarea) {
        String sql = "UPDATE Tareas SET estado_id = 3 WHERE id = ?";
        try (Connection con = DBUtils.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idTarea);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar estado: " + e.getMessage());
            return false;
        }
    }
}