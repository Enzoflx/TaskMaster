package Utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase de utilidad para gestionar la conexión con la base de datos MySQL de TasksMaster.
 * 
 * @author Enzo Berzosa
 * @version 1.0
 */
public class DBUtils {

    private static final String URL = "jdbc:mysql://localhost:3306/TasksMaster";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345678";

    /**
     * Constructor privado para evitar la instanciación de esta clase de utilidad.
     */
    private DBUtils() {
        throw new UnsupportedOperationException("Clase de utilidad");
    }

    /**
     * Establece y devuelve una conexión activa a la base de datos MySQL local de TasksMaster.
     * 
     * @return Un objeto Connection activo.
     * @throws SQLException Si ocurre algún error al intentar conectar con la base de datos.
     */
    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
