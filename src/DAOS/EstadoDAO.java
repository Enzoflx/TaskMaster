package DAOS;

import POJOS.Estado;
import Utilidades.DBUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstadoDAO {

    // Permite obtener un estado por ID, es muy útil para gestionarlos.
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

    // Devuelve una lista con todos los estados disponibles.
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