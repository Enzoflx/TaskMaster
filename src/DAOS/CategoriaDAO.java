package DAOS;

import POJOS.Categoria;
import Utilidades.DBUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de Acceso a Datos (DAO) para realizar operaciones sobre la entidad Categoría.
 * Permite listar y obtener categorías de la base de datos.
 * 
 * @author Enzo Berzosa
 * @version 1.0
 */
public class CategoriaDAO {

    /**
     * Constructor por defecto de CategoriaDAO.
     */
    public CategoriaDAO() {
    }
    
    /**
     * Obtiene una categoría concreta por su identificador único.
     * 
     * @param id El identificador único de la categoría.
     * @return El objeto Categoria si existe; null si no se encuentra o hay error.
     */
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
        } catch (SQLException e) { 
            System.out.println("Error: " + e.getMessage()); 
        }
        return null;
    }

    /**
     * Devuelve una lista con todas las categorías registradas en la base de datos.
     * 
     * @return Lista de objetos Categoria.
     */
    public List<Categoria> listarCategorias() {
        List<Categoria> lista = new ArrayList<>();
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
