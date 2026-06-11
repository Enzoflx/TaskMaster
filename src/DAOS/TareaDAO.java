package DAOS;

import POJOS.*;
import Utilidades.DBUtils;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de Acceso a Datos (DAO) para gestionar las operaciones CRUD y de negocio
 * sobre la entidad Tarea en la base de datos.
 * 
 * @author Enzo Berzosa
 * @version 1.0
 */
public class TareaDAO {

    /**
     * Constructor por defecto de TareaDAO.
     */
    public TareaDAO() {
    }
    
    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private EstadoDAO estadoDAO = new EstadoDAO();
    private CategoriaDAO categoriaDAO = new CategoriaDAO();

    /**
     * Mapea una fila actual del ResultSet a un objeto de tipo Tarea.
     * Centraliza la lógica de reconstrucción de objetos asociados.
     * 
     * @param rs El ResultSet que contiene la fila de datos.
     * @return El objeto Tarea instanciado.
     * @throws SQLException Si ocurre un error de lectura en el ResultSet.
     */
    private Tarea mapResultSetToTarea(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String titulo = rs.getString("titulo");
        String descripcion = rs.getString("descripcion");
        LocalDateTime fechaCreacion = rs.getTimestamp("fecha_creacion").toLocalDateTime();
        LocalDateTime fechaLimite = rs.getTimestamp("fecha_limite") != null 
                ? rs.getTimestamp("fecha_limite").toLocalDateTime() 
                : null;
        Estado estado = estadoDAO.obtenerPorId(rs.getInt("estado_id"));
        Usuario usuario = usuarioDAO.obtenerPorId(rs.getInt("usuario_propietario_id"));
        Categoria categoria = categoriaDAO.obtenerPorId(rs.getInt("categoria_id"));
        String observaciones = rs.getString("observaciones");

        return new Tarea(id, titulo, descripcion, fechaCreacion, fechaLimite, estado, usuario, categoria, observaciones);
    }

    /**
     * Inserta una nueva tarea en la base de datos.
     * 
     * @param tarea La tarea a insertar.
     * @return true si la inserción fue exitosa; false en caso contrario.
     */
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

    /**
     * Obtiene una lista de todas las tareas pertenecientes a un usuario concreto.
     * 
     * @param idUsuario El identificador del usuario.
     * @return Una lista de tareas del usuario especificado.
     */
    public List<Tarea> listarTareasPorUsuario(int idUsuario) {
        List<Tarea> lista = new ArrayList<>();
        String sql = "SELECT * FROM Tareas WHERE usuario_propietario_id = ?";

        try (Connection con = DBUtils.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idUsuario);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapResultSetToTarea(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al listar tareas: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Elimina una tarea existente de la base de datos utilizando su identificador.
     * 
     * @param idTarea El identificador de la tarea a eliminar.
     * @return true si la tarea se eliminó con éxito; false en caso contrario o si no existía.
     */
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

    /**
     * Marca una tarea existente como COMPLETADA actualizando su estado en la base de datos.
     * 
     * @param idTarea El identificador de la tarea a completar.
     * @return true si el estado se actualizó correctamente; false en caso contrario.
     */
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

    /**
     * Obtiene una tarea concreta a partir de su identificador único.
     * 
     * @param idTarea El identificador de la tarea deseada.
     * @return El objeto Tarea si se encuentra en la base de datos; null si no existe o hay un error.
     */
    public Tarea obtenerPorId(int idTarea) {
        String sql = "SELECT * FROM Tareas WHERE id = ?";
        try (Connection con = DBUtils.getConexion();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idTarea);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToTarea(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener tarea: " + e.getMessage());
        }
        return null;
    }

    /**
     * Actualiza los datos de una tarea existente en la base de datos.
     * 
     * @param tarea La tarea con los datos actualizados a persistir.
     * @return true si la actualización fue exitosa; false en caso contrario.
     */
    public boolean actualizar(Tarea tarea) {
        String sql = "UPDATE Tareas SET titulo = ?, descripcion = ?, fecha_limite = ?, estado_id = ?, categoria_id = ?, observaciones = ? WHERE id = ?";
        try (Connection con = DBUtils.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
             ps.setString(1, tarea.getTitulo());
             ps.setString(2, tarea.getDescripcion());
            if (tarea.getFechaLimite() != null) {
                ps.setTimestamp(3, Timestamp.valueOf(tarea.getFechaLimite()));
            } else {
                ps.setNull(3, java.sql.Types.TIMESTAMP);
            }
            ps.setInt(4, tarea.getEstado().getId());
            ps.setInt(5, tarea.getCategoria().getId());
            ps.setString(6, tarea.getObservaciones());
            ps.setInt(7, tarea.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar tarea: " + e.getMessage());
            return false;
        }
    }

    /**
     * Lista y filtra las tareas de un usuario en base a una categoría específica.
     * 
     * @param idUsuario El identificador del usuario.
     * @param idCategoria El identificador de la categoría de interés.
     * @return Una lista de tareas que pertenecen a dicho usuario y categoría.
     */
    public List<Tarea> listarTareasPorUsuarioYcategoria(int idUsuario, int idCategoria) {
        List<Tarea> lista = new ArrayList<>();
        String sql = "SELECT * FROM Tareas WHERE usuario_propietario_id = ? AND categoria_id = ?";
        try (Connection con = DBUtils.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
             ps.setInt(1, idUsuario);
             ps.setInt(2, idCategoria);
             try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapResultSetToTarea(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al filtrar tareas: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Lista y filtra las tareas de un usuario en base a un estado específico.
     * 
     * @param idUsuario El identificador del usuario.
     * @param idEstado El identificador del estado de interés.
     * @return Una lista de tareas que pertenecen a dicho usuario y estado.
     */
    public List<Tarea> listarTareasPorEstado(int idUsuario, int idEstado) {
        List<Tarea> lista = new ArrayList<>();
        String sql = "SELECT * FROM Tareas WHERE usuario_propietario_id = ? AND estado_id = ?";

        try (Connection con = DBUtils.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
             ps.setInt(1, idUsuario);
             ps.setInt(2, idEstado);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapResultSetToTarea(rs));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al filtrar tareas por estado: " + e.getMessage());
        }
        return lista;
    }
}
