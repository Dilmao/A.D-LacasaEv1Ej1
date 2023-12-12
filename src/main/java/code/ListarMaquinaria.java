package code;

import libs.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListarMaquinaria {
    public static void listarMaquinaria() {
        try (Connection miCon = ConexionBD.conectar()) {
            // Consulta SQL para obtener la maquinaria
            String maquinas = "SELECT * FROM maquinaria";
            PreparedStatement statement = miCon.prepareStatement(maquinas);
            ResultSet resultSet = statement.executeQuery();

            // Mostrar la maquinaria
            while (resultSet.next()) {
                String matricula = resultSet.getString("matricula");
                String empleado = resultSet.getString("empleado");
                String nombreObra = resultSet.getString("nombreObra");

                // Imprimir los detalles de la maquina
                System.out.println("Matricula: " + matricula);
                System.out.println("    Empleado asignado: " + empleado);
                System.out.println("    Obra asignada: " + nombreObra);
            }
        } catch (SQLException e) {
            System.err.println(">>> Error: " + e.getMessage());
        }
    }
}
