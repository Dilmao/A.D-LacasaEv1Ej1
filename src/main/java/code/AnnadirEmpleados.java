package code;

import libs.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnnadirEmpleados {
    public static void annadirEmpleado() {
        String DNI = libs.Leer.pedirCadena("Introduzca el DNI del empleado");
        String nombre = libs.Leer.pedirCadena("Introduzca el nombre del empleado");
        float sueldo = libs.Leer.pedirFloat("Introduzca el sueldo del empleado");
        String nombreObra = libs.Leer.pedirCadena("Introduzca la obra en la que trabaja el empleado");

        boolean DNIValido = true;
        boolean nombreValido = true;
        boolean nombreObraValido = true;

        try (Connection miCon = ConexionBD.conectar()) {
            // Select de empleados
            String empleados = "SELECT * FROM empleado";
            PreparedStatement statementEmpleados = miCon.prepareStatement(empleados);
            ResultSet resultSetEmpleados = statementEmpleados.executeQuery();
            // Select de obras
            String obras = "SELECT * FROM obra";
            PreparedStatement statementObras = miCon.prepareStatement(obras);
            ResultSet resultSetObras = statementObras.executeQuery();

            // Se comprueba si el DNI introducido es valido (no se comprueba el formato)
            while (resultSetEmpleados.next()) {
                String DNIEmpleado = resultSetEmpleados.getString("dni");
                if (DNI.equals(DNIEmpleado)) {
                    DNIValido = false;
                }
            }

            // Se comprueba si el nombre introducido es valido
            if (nombre == null) {
                nombreValido = false;
            }

            // Se comprueba si la obra introducida es valida
            while (resultSetObras.next()) {
                String obra = resultSetObras.getString("nombre");
                if (nombreObra.equals(obra)) {
                    nombreObraValido = false;
                }
            }

            if (DNIValido && nombreValido && nombreObraValido) {
                String nuevoEmpleado = "INSERT INTO empleados VALUES (?, ?, ?, ?)";
                PreparedStatement statementNuevoEmpleado = miCon.prepareStatement(nuevoEmpleado);

                // Valores del nuevo empleado
                statementNuevoEmpleado.setString(1, DNI);
                statementNuevoEmpleado.setString(2, nombre);
                statementNuevoEmpleado.setFloat(3, sueldo);
                statementNuevoEmpleado.setString(4, nombreObra);

                // Ejecucion del statment
                statementNuevoEmpleado.execute();
            } else if (!DNIValido) {
                System.out.println("El DNI introducido ya existe en la base de datos");
            } else if (!nombreValido) {
                System.out.println("El nombre introducido esta vacio");
            } else {
                System.out.println("La obra asignada no existe");
            }
        } catch (SQLException e) {
            System.err.println(">>> Error: " + e.getMessage());
        }
    }
}
