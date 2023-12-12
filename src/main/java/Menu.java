public class Menu {
    public static void main(String[] args) {
        // Problemas en la conexion de la base de datos ('libs/ConexionBD')
        boolean salir = false;
        String opcion;

        while (!salir) {
            System.out.println("\n*****************************************************************");
            System.out.println("0. Salir");
            System.out.println("1. Crear base de datos");
            System.out.println("2. Listar la maquinaria");
            System.out.println("3. Añadir nuevos empleados");
            System.out.println("*****************************************************************");
            opcion = libs.Leer.pedirCadena("Introduzca una opcion");

            switch (opcion) {
                case "0":
                    salir = true;
                    break;
                case "1":
                    code.EstructuraDB.crearBD();
                    code.EstructuraDB.crearTablas();
                    break;
                case "2":
                    code.ListarMaquinaria.listarMaquinaria();
                    break;
                case "3":   // TODO
                    code.AnnadirEmpleados.annadirEmpleado();
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        }
    }
}
