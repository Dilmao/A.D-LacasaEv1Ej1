package code;

import libs.ConexionBD;

import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class EstructuraDB {
    // Creación de la base de datos
    public static void crearBD() {
        try (Connection miCon = ConexionBD.conectar()) {
            if (miCon != null) {
                DatabaseMetaData meta = miCon.getMetaData();
                System.out.println("Base de datos creada");
            }
        } catch (SQLException e) {
            System.err.println(">>> Error en la conexion a la base de datos");
        }
    }

    public static void crearTablas() {
        crearTablaObra();
        crearTablaEmpleado();
        crearTablaMaquinaria();
    }

    // Creación de las tablas
    public static void crearTablaObra() {
        try(Connection miCon = ConexionBD.conectar()) {
            // Sentencia SQL para crear tabla obra
            String tablaObra = "CREATE TABLE obra (\n" +
                    "id INT PRIMARY KEY,\n" +
                    "nombre VARCHAR(50) NOT NULL UNIQUE,\n" +
                    "direccion VARCHAR(50) NOT NULL,\n" +
                    "entrega DATE\n" +
                    ");";

            // Sentencias SQL para añadir valores a la tabla
            List<String> addObras = Arrays.asList(
                    "INSERT INTO obra VALUES (1,'URBANIZACIÓN BUENAVISTA','C/MAYOR 3', '2023/03/02');",
                    "INSERT INTO obra VALUES (2,'RESIDENCIAL MIRAFLORES','C/MENOR 2', '2025/05/02');",
                    "INSERT INTO obra VALUES (3,'BAR EL PEDREGAL','C/OSA 12', '2024/05/02');",
                    "INSERT INTO obra VALUES (4,'POLIDEPORTIVO MUNICIPAL','C/FRONTÓN 25', '2024/10/20');"
            );

            // Variable Statement para ejecutar las sentencias SQL en la conexión
            Statement crearTablaObras = miCon.createStatement();

            // Borrado de la tabla antes de volver a crearla
            crearTablaObras.executeUpdate("DROP TABLE IF EXIST obra");
            crearTablaObras.executeUpdate(tablaObra);

            // Se añade los contenidos a la tabla
            for (String obra : addObras) {
                crearTablaObras.executeUpdate(obra);
            }
        } catch (SQLSyntaxErrorException e) {
            System.err.println(">>> Error en la sintaxis de la sentencai SQL(1): " + e.getMessage());
        } catch (SQLIntegrityConstraintViolationException e) {
            System.err.println(">>> Error: La sentencia SQL no cumple con los requisitos de integridad de la base de datos(1): " + e.getMessage());
        } catch (SQLException e) {
            System.err.println(">>> Error: No se puede conectar a la base de datos (1): " + e.getMessage());
        }
    }         // 1

    public static void crearTablaEmpleado() {
        try(Connection miCon = ConexionBD.conectar()) {
            // Sentencia SQL para crear tabla obra
            String tablaEmpleado = "CREATE TABLE empleado (\n" +
                    "dni VARCHAR(9) PRIMARY KEY,\n" +
                    "nombre VARCHAR(50) NOT NULL,\n" +
                    "sueldo FLOAT,\n" +
                    "nombreObra VARCHAR(50),\n" +
                    "CONSTRAINT fk_obraEmp FOREIGN KEY (nombreObra) REFERENCES obra(nombre)\n" +
                    ");";

            // Sentencias SQL para añadir valores a la tabla
            List<String> addEmpleados = Arrays.asList(
                    "INSERT INTO empleado VALUES ('17123456A', 'PEDRO PICAPIEDRA', 3000.0, 'RESIDENCIAL MIRAFLORES');",
                    "INSERT INTO empleado VALUES ('19123456B', 'PABLO MARMOL', 2000.0, 'RESIDENCIAL MIRAFLORES');"
            );

            // Variable Statement para ejecutar las sentencias SQL en la conexión
            Statement crearTablaEmpleados = miCon.createStatement();

            // Borrado de la tabla antes de volver a crearla
            crearTablaEmpleados.executeUpdate("DROP TABLE IF EXIST empleado");
            crearTablaEmpleados.executeUpdate(tablaEmpleado);

            // Se añade los contenidos a la tabla
            for (String empleado : addEmpleados) {
                crearTablaEmpleados.executeUpdate(empleado);
            }
        } catch (SQLSyntaxErrorException e) {
            System.err.println(">>> Error en la sintaxis de la sentencai SQL(2): " + e.getMessage());
        } catch (SQLIntegrityConstraintViolationException e) {
            System.err.println(">>> Error: La sentencia SQL no cumple con los requisitos de integridad de la base de datos(2): " + e.getMessage());
        } catch (SQLException e) {
            System.err.println(">>> Error: No se puede conectar a la base de datos (2): " + e.getMessage());
        }
    }     // 2

    public static void crearTablaMaquinaria() {
        try(Connection miCon = ConexionBD.conectar()) {
            // Sentencia SQL para crear tabla obra
            String tablaMaquinaria = "CREATE TABLE maquinaria (\n" +
                    "matricula VARCHAR(7) PRIMARY KEY,\n" +
                    "modelo VARCHAR(20) NOT NULL,\n" +
                    "empleado  varchar(9),\n" +
                    "nombreObra VARCHAR(50),\n" +
                    "CONSTRAINT fk_empleado FOREIGN KEY (empleado) REFERENCES empleado(dni),\n" +
                    "CONSTRAINT fk_obraMaq FOREIGN KEY (nombreObra) REFERENCES obra(nombre)\n" +
                    ");";

            // Sentencias SQL para añadir valores a la tabla
            List<String> addMaquinaria = Arrays.asList(
                    "INSERT INTO maquinaria VALUES ('1234BCD', 'EXCAVADORA 2000', '17123456A','RESIDENCIAL MIRAFLORES');",
                    "INSERT INTO maquinaria VALUES ('4567XYZ', 'MANITOU 3000', '19123456B', 'RESIDENCIAL MIRAFLORES');"
            );

            // Variable Statement para ejecutar las sentencias SQL en la conexión
            Statement crearTablaMaquinaria = miCon.createStatement();

            // Borrado de la tabla antes de volver a crearla
            crearTablaMaquinaria.executeUpdate("DROP TABLE IF EXIST maquinaria");
            crearTablaMaquinaria.executeUpdate(tablaMaquinaria);

            // Se añade los contenidos a la tabla
            for (String maquina : addMaquinaria) {
                crearTablaMaquinaria.executeUpdate(maquina);
            }
        } catch (SQLSyntaxErrorException e) {
            System.err.println(">>> Error en la sintaxis de la sentencai SQL(2): " + e.getMessage());
        } catch (SQLIntegrityConstraintViolationException e) {
            System.err.println(">>> Error: La sentencia SQL no cumple con los requisitos de integridad de la base de datos(2): " + e.getMessage());
        } catch (SQLException e) {
            System.err.println(">>> Error: No se puede conectar a la base de datos (2): " + e.getMessage());
        }
    }   // 3
}
