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

    public java.util.List<Categoria> listarCategorias() {
        java.util.List<Categoria> lista = new java.util.ArrayList<>();
        String sql = "SELECT * FROM Categorias";
        try (Connection con = DBUtils.getConexion();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Categoria(rs.getInt("id"), rs.getString("nombre"), rs.getString("descripcion")));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar categorías: " + e.getMessage());
        }
        return lista;
    }
}