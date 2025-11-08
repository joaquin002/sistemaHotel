import Clase.SistemaHotel;
import Excepcion.DuplicadoEx;
import Excepcion.NoRegistradoEx;
import Excepcion.UsuarioNoEncontradoEx;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        // prueba de usuario funciona
        SistemaHotel sistema = new SistemaHotel();
        sistema.registrarUsuario(1, "paulina", "pau1234", 1);
        try {
            sistema.iniciarSesion("paulina", "pau1234");
            sistema.iniciarSesion("paulina", "pau4");
        } catch (UsuarioNoEncontradoEx e) {
            System.out.println(e.getMessage());
        }

        //cargar el hotel que vamos a estar administrando
        sistema.cargarHotel(1, "BellaVista", "Avenida Siempre Viva");
        sistema.cargarHotel(1, "BellaVista", "Avenida Siempre Viva");  //prueba de que no podemos cargar mas de un hotel.
        //prueba de mostrar la informacion del hotel
        System.out.println(sistema.mostrarHotel(1));

        //menuPrincipal(sistema);



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

            switch (opcion) {
                case 1:
                    menuInterno(sistema);
                    break;
                case 2:

                    ingresarDatos();
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
        String name = "";
        String password = "";
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

            switch (opcion) {
                case 1:
                    ingresarDatos();
                    sistema.registrarUsuario(1, name, password, 1);
                    break;
                case 2:
                    ingresarDatos();
                    sistema.registrarUsuario(1, name, password, 2);
                    break;
                case 3:
                    ingresarDatos();
                    sistema.registrarUsuario(1, name, password, 3);
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

                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
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

        do {
            System.out.println("=============================================");
            System.out.println("      Administrador   ");
            System.out.println("---------------------------------------------");
           // System.out.println("1. Cargar Hotel");//REVISAR. porque creo que el admin no deberia cargar el hotel pq es el sistema para 1 hotel unico e irrepetible. yo lo cargaria en el main y listo.
            System.out.println("2. Cargar recepcionista");//listo
            System.out.println("3. Eliminar hotel");//listo
            System.out.println("4. Eliminar  recepcionista");//listo
            System.out.println("5. mostrar hotel");//listo
            System.out.println("6. Buscar recepcionista");//listo
            System.out.println("7. cargar habitacion en un hotel");//listo
            System.out.println("8. ver habitaciones del hotel");//listo
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    //aca agrego los metodos correspondientes cuando los tenga
                    sistema.cargarHotel(1, "hotel 1", "calle nueva"); // REVISAR
                    break;
                case 2:
                    sistema.cargarRecepcionista(1);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    System.out.println(sistema.mostrarHotel(1)); //PEDIRLE AL USUARIO EL ID
                    break;
                case 6:
                    System.out.println(sistema.verRecepcionista(1));
                    break;
                case 7:
                    sistema.cargarHabitacionEstandar(123, 1200, "muy buena", "varios", 2, true);
                    break;
                case 8:
                    System.out.println(sistema.mostrarHabitacion(123));
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
}