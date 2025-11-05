import Clase.SistemaHotel;
import Excepcion.UsuarioNoEncontradoEx;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        /// prueba de usuario funciona
        SistemaHotel sistema = new SistemaHotel();
        sistema.registrarUsuario("paulina", "pau1234", 1);
        try {
            sistema.iniciarSesion("paulina", "pau1234");
            sistema.iniciarSesion("paulina", "pau4");
        } catch (UsuarioNoEncontradoEx e) {
            System.out.println(e.getMessage());
        }

        menuPrincipal(sistema);

    }

    public static void menuPrincipal(SistemaHotel sistema)
    {
        Scanner sc = new Scanner(System.in);
        int opcion=0;
        String nombre="";
        String contrasenia="";

        char seguir='s';

        do {
            System.out.println("Hotel BellaVista.com");  // Hago un poco de decoracion para que se vea mas prolijo como si fuese un programa real
            System.out.println("\n=============================================");
            System.out.println("🏨  BIENVENIDO A HOTEL BELLAVISTA  🏨");
            System.out.println("=============================================");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Iniciar Sesion");
            System.out.println("3. Salir");
            System.out.println("Seleccione una opción...");
            opcion = sc.nextInt();

            switch (opcion)
            {
                case 1:
                    menuInterno(sistema);
                    break;
                case 2:
                    ingresarDatos(nombre, contrasenia);
                    try {
                        sistema.iniciarSesion(nombre, contrasenia);
                        System.out.println("¡Sesion iniciado con exito!");
                    } catch (UsuarioNoEncontradoEx e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Gracias por usar nuestro sistema. ¡Hasta pronto!");
                    seguir='n';
                    break;
                default:
                    System.out.println("Error. Opcion no valida. Intente nuevamente.");
                    break;
            }
            if (opcion!=3){
                System.out.println("\n¿Desea realizar otra accion? (s/n): ");
                seguir=sc.next().charAt(0);
            }
        }while (seguir=='s' && opcion!=3);
    }

    // tengo que llamar en los parametros al sistema creado en el main porque sino lo que yo cargue en estos metodos no se va a guardar.
    public static void menuInterno(SistemaHotel sistema)
    {
        Scanner sc = new Scanner(System.in);
        int opcion=0;
        String name="";
        String password="";
        char seguir='s';
        boolean encontrado=false;
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

            switch (opcion)
            {
                case 1:
                    ingresarDatos(name,password);
                    sistema.registrarUsuario(name,password,1);
                    break;
                case 2:
                    ingresarDatos(name,password);
                    sistema.registrarUsuario(name,password,2);
                    break;
                case 3:
                    ingresarDatos(name,password);
                    sistema.registrarUsuario(name,password,3);
                    break;
                case 4:
                    encontrado=true;
                    break;
                default:
                    System.out.println("Error. Opcion no valida...");
                    break;
            }
            if (encontrado==false){
                System.out.println("¿Desea registrarse en otro modo?");
                seguir=sc.next().charAt(0);
            }
        }while (seguir=='s' && encontrado==false);


    }

    public static void ingresarDatos(String name, String password)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Ingrese nombre de usuario:");
        name=sc.nextLine();
        System.out.println("Ingrese contrasena:");
        password=sc.nextLine();
    }
}