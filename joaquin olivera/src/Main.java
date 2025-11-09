import Clase.SistemaHotel;
import Enums.MetodoPago;
import Enums.ServicioEsepcialDeluxe;
import Enums.ServicioEspecialSuite;
import Excepcion.DuplicadoEx;
import Excepcion.NoRegistradoEx;
import Excepcion.UsuarioNoEncontradoEx;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {

        SistemaHotel sistema = new SistemaHotel();
        menuPrincipal(sistema);



    }

    // menu principal. va a contener el resto de los metodos necesarios para que funcione
    public static void menuPrincipal(SistemaHotel sistema) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        String nombre = "";
        String contrasenia = "";

        char seguir = 's';

        do {
            System.out.println("Hotel BellaVista.com");
            System.out.println("\n=============================================");
            System.out.println("🏨  BIENVENIDO A HOTEL BELLAVISTA  🏨");
            System.out.println("=============================================");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Iniciar Sesion");
            System.out.println("3. Salir");
            System.out.println("Seleccione una opción...");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    menuInterno(sistema);
                    break;
                case 2:
                    //ingresarDatos();
                    System.out.println("Ingrese nombre de usuario:");
                    nombre = sc.nextLine();
                    System.out.println("Ingrese contrasena:");
                    contrasenia = sc.nextLine();
                    try {
                        String modo = sistema.iniciarSesion(nombre, contrasenia);
                        System.out.println("Inicio sesion correctamente como " + modo);
                        if (modo.equals("Recepcionista")) {
                            menuRecepcionista(sistema);
                        }
                        if (modo.equals("Administrador")) {
                            menuAdministrador(sistema);
                        }
                        if (modo.equals("Cliente")) {
                            menuCliente(sistema);
                        }
                    } catch (UsuarioNoEncontradoEx e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Gracias por usar nuestro sistema. ¡Hasta pronto!");
                    seguir = 'n';
                    break;
                default:
                    System.out.println("Error. Opcion no valida. Intente nuevamente.");
                    break;
            }
            if (opcion != 3) {
                System.out.println("\n¿Desea realizar otra accion? (s/n): ");
                seguir = sc.next().charAt(0);
            }
        } while (seguir == 's');
    }

    // tengo que llamar en los parametros al sistema creado en el main porque sino lo que yo cargue en estos metodos no se va a guardar.
    public static void menuInterno(SistemaHotel sistema) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        char seguir = 's';
        boolean encontrado = false;
        do {
            System.out.println("=============================================");
            System.out.println("          Registrando usuario  ");
            System.out.println("=============================================");
            System.out.println("Seleccione modo de acceso:");
            System.out.println("1. Administrador");
            System.out.println("2. Cliente");
            System.out.println("3. Recepcionista");
            System.out.println("4. salir");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                   // ingresarDatos();
                    System.out.println("Ingrese nombre de usuario:");
                    String nameAdmin = sc.nextLine();
                    System.out.println("Ingrese contrasena:");
                    String passAdmin = sc.nextLine();
                    sistema.registrarUsuario(1, nameAdmin, passAdmin, 1);
                    break;
                case 2:
                    //ingresarDatos();
                    System.out.println("Ingrese nombre de usuario:");
                    String nameCliente = sc.nextLine();
                    System.out.println("Ingrese contrasena:");
                    String passCliente = sc.nextLine();
                    sistema.registrarUsuario(1, nameCliente, passCliente, 2);
                    break;
                case 3:
                    //ingresarDatos();
                    System.out.println("Ingrese nombre de usuario:");
                    String nameRecep = sc.nextLine();
                    System.out.println("Ingrese contrasena:");
                    String passRecep = sc.nextLine();
                    sistema.registrarUsuario(1, nameRecep, passRecep, 3);
                    break;
                case 4:
                    encontrado = true;
                    break;
                default:
                    System.out.println("Error. Opcion no valida...");
                    break;
            }
            if (!encontrado) {
                System.out.println("¿Desea registrarse en otro modo?");
                seguir = sc.next().charAt(0);
            }
        } while (seguir == 's' && !encontrado);


    }

    public static void ingresarDatos() {
        String name;
        String password;
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese nombre de usuario:");
        name = sc.nextLine();
        System.out.println("Ingrese contrasena:");
        password = sc.nextLine();
    }

    public static void menuRecepcionista(SistemaHotel sistema) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        char seguir = 's';

        do {
            System.out.println("=============================================");
            System.out.println("        Recepcionista    ");
            System.out.println("---------------------------------------------");
            System.out.println("1. Hacer CheckIn");
            System.out.println("2. Hacer CheckOut");
            System.out.println("3. Consultar disponibilidad");
            System.out.println("4. Buscar reserva");
            System.out.println("5. Buscar cliente");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    //aca agrego los metodos correspondientes cuando los tenga
                    //pedirle datos al usuario
                    sistema.checkIn(1234, 1, "07/11/2025", "pau", "domicilio", MetodoPago.EFECTIVO);
                    break;
                case 2:
                    //pedirle datos al usuario
                    sistema.checkOut(1, 1234, "15/11/2025");
                    break;
                case 3:
                    //consultar disponibilidad
                    System.out.println(sistema.consultarDisponibilidad());
                    break;
                case 4:
                    //buscar reserva
                    System.out.println(sistema.buscarReserva(1)); //probar
                    break;
                case 5:
                    //buscar cliente
                    System.out.println(sistema.buscarCliente(1)); //probar
                    break;
                default:
                    break;
            }
            System.out.println("¿Desea elegir otra opcion?");
            seguir = sc.next().charAt(0);


        } while (seguir == 's');
    }

    public static void menuAdministrador(SistemaHotel sistema) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        char seguir = 's';
        boolean encontrado=false;
        do {
            System.out.println("=============================================");
            System.out.println("      Administrador   ");
            System.out.println("---------------------------------------------");
            System.out.println("1. Mostrar hotel");//listo
            System.out.println("2. Cargar recepcionista");//listo
            System.out.println("3. Eliminar  recepcionista");//listo
            System.out.println("4. Buscar recepcionista");//listo
            System.out.println("5. Ver recepcionista");
            System.out.println("6. Cargar habitacion");//listo
            System.out.println("7. Buscar habitacion");
            System.out.println("8. Ver habitaciones del hotel");//listo
            System.out.println("9. Eliminar habitacion");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    //funciona
                    System.out.println(sistema.mostrarHotel());
                    break;
                case 2:
                    sistema.cargarRecepcionista(1);
                    break;
                case 3:
                    //eliminar recepcionista
                    encontrado=sistema.eliminarRecepcionista();
                    if (encontrado==true){
                        System.out.println("se elimino el recepcionista");
                    }else {
                        System.out.println("no se elimino el recepcionista");
                    }
                    break;
                case 4:
                    //buscar recepcionista no se si va
                    break;
                case 5:
                    //ver recepcionista
                    System.out.println(sistema.verRecepcionista(1));
                    break;
                case 6:
                    //cargar habitacion funciona
                   menuCargarHabitacion(sistema);
                    break;
                case 7:
                    //buscar y mostrar habitacion funciona
                    System.out.println(sistema.mostrarHabitacion(123));
                    break;
                case 8:
                   //ver todas las habitaciones funciona
                    System.out.println(sistema.mostrarHabitaciones());
                    break;
                case 9:
                    //sistema.eliminarHabitacion(); pedir el id al usuario
                    break;
                default:
                    break;
            }
            System.out.println("¿Desea elegir otra opcion?");
            seguir = sc.next().charAt(0);


        } while (seguir == 's');
    }

    public static void menuCliente(SistemaHotel sistema1) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        char seguir = 's';

        do {
            System.out.println("=============================================");
            System.out.println("        Cliente     ");
            System.out.println("---------------------------------------------");
            System.out.println("1. Hacer reserva");
            System.out.println("2. Ver puntos disponibles");
            System.out.println("3. Canjear puntos disponibles");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    //aca agrego los metodos correspondientes cuando los tenga
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:
                    break;
            }
            System.out.println("¿Desea elegir otra opcion?");
            seguir = sc.next().charAt(0);


        } while (seguir == 's');
    }

    public static void menuCargarHabitacion(SistemaHotel sistema) {
        Scanner sc = new Scanner(System.in);
        int opcion=0;
        char seguir='s';
        do {
            System.out.println("Seleccione el tipo de habitacion a cargar: ");
            System.out.println("1. Estandar");
            System.out.println("2. Deluxe");
            System.out.println("3. Suite");
            System.out.println("4. Volver al menu anterior");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion){
                case 1:
                    //estandar
                    //pedir datos al usuario
                    sistema.cargarHabitacionEstandar(123, 1200, "muy buena", "varios", 2, true);
                    System.out.println("se agrego con exito la habitacion estandar");
                    break;
                case 2:
                    //deluxe
                    //pedir datos al usuario
                    sistema.cargarHabitacionDeluxe(12, 2000, "desc", "servicios", 1, ServicioEsepcialDeluxe.BAR, true);
                    System.out.println("se agrego con exito la habitacion deluxe");
                    break;
                case 3:
                    //suite
                    //pedir datos al usuario
                    sistema.cargarHabitacionSuite(2, 50000, "desc", "servicios", 1, ServicioEspecialSuite.CINE_INTERACTIVO, true);
                    System.out.println("se agrego con exito la habitacion suite");
                    break;
                case 4:
                    seguir='n';
                    break;
                default:
                    System.out.println("opcion invalida");
                    break;
            }
            if (opcion!=4){
                System.out.println("Desea cargar otra habitacion? (s/n)");
                seguir= sc.next().charAt(0);
                sc.nextLine();
            }
        }while (seguir == 's');

    }
}