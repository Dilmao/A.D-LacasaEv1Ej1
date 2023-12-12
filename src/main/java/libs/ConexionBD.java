package libs;

import java.lang.module.InvalidModuleDescriptorException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:mariadb://localhost:3909/constructora";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";
    public static Connection conectar() {
        Connection conexion = null;

        try {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println(">>> Error en la conexion: " + e.getMessage());
        } catch (InvalidModuleDescriptorException e) {
            System.err.println(">>> Error PAM");
        }

        return conexion;
    }
}
