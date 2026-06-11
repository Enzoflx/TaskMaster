package DAOS;

import POJOS.Estado;
import Utilidades.DBUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de Acceso a Datos (DAO) para realizar operaciones sobre la entidad Estado.
 * Permite listar y obtener estados de tareas de la base de datos.
 * 
 * @author Enzo Berzosa
 * @version 1.0
 */
public class EstadoDAO {

    /**
     * Constructor por defecto de EstadoDAO.
     */
    public EstadoDAO() {
    }

    /**
     * Obtiene un estado concreto por su identificador único.
     * 
     * @param id El identificador único del estado.
     * @return El objeto Estado si existe; null si no se encuentra o hay error.
     */
    public Estado obtenerPorId(int id) {
        String sql = "SELECT * FROM Estados WHERE id = ?";
        try (Connection con = DBUtils.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Estado(rs.getInt("id"), rs.getString("nombre"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener estado: " + e.getMessage());
        }
        return null;
    }

    /**
     * Devuelve una lista con todos los estados disponibles en la base de datos.
     * 
     * @return Lista de objetos Estado.
     */
    public List<Estado> listarEstados() {
        List<Estado> lista = new ArrayList<>();
        String sql = "SELECT * FROM Estados";
        try (Connection con = DBUtils.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Estado(rs.getInt("id"), rs.getString("nombre")));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar estados: " + e.getMessage());
        }
        return lista;
    }
}
