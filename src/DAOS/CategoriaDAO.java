package DAOS;

import POJOS.Categoria;
import Utilidades.DBUtils;
import java.sql.*;

public class CategoriaDAO {
    public Categoria obtenerPorId(int id) {
        String sql = "SELECT * FROM Categorias WHERE id = ?";
        try (Connection con = DBUtils.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Categoria(rs.getInt("id"), rs.getString("nombre"), rs.getString("descripcion"));
                }
            }
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
        return null;
    }
}