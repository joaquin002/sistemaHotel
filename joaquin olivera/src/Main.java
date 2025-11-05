import Clase.SistemaHotel;
import Excepcion.UsuarioNoEncontradoEx;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        /// prueba de usuario funciona


        SistemaHotel s1 = new SistemaHotel();
        s1.registrarUsuario("paulina", "pau1234", 1);
        try {
            s1.iniciarSesion("paulina", "pau1234");
            s1.iniciarSesion("paulina", "pau4");
        } catch (UsuarioNoEncontradoEx e) {
            System.out.println(e.getMessage());
        }




       // menudos();
        //menu();



    }

    public static void menuPrincipal()
    {
        Scanner sc = new Scanner(System.in);
        int opcion=0;
        String nombre="";
        String contrasenia="";
        char seguir='s';
        boolean encontrar=false;
        SistemaHotel s1 = new SistemaHotel();

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

            switch (opcion)
            {
                case 1:
                    menudos();
                    break;
                case 2:
                    ingresarDatos(nombre, contrasenia);
                    try {
                        s1.iniciarSesion(nombre, contrasenia);
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

    public static void menudos()
    {
        SistemaHotel s1 = new SistemaHotel();
        Scanner sc = new Scanner(System.in);
        int opcion=0;
        String name="";
        String password="";
        char seguir='s';
        boolean encontrado=false;
        do {
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
                    s1.registrarUsuario(name,password,1);
                    break;
                case 2:
                    ingresarDatos(name,password);
                    s1.registrarUsuario(name,password,2);
                    break;
                case 3:
                    ingresarDatos(name,password);
                    s1.registrarUsuario(name,password,3);
                    break;
                case 4:
                    encontrado=true;
                    break;
                default:
                    System.out.println("Error. Opcion no valida...");
                    break;
            }
            if (encontrado==false){
                System.out.println("¿Desea elegir otra opcion?");
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