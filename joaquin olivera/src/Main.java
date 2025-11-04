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
            System.out.println(s1.iniciarSesion("paulina", "pau1234"));
            System.out.println(s1.iniciarSesion("paulina", "pau4"));
        } catch (UsuarioNoEncontradoEx e) {
            System.out.println(e.getMessage());
        }
        menudos();



    }

    /*public void menu()
    {
        int opcion;
        do {
            System.out.println("HOTEL");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Iniciar Sesion");
            System.out.println("3. Salir");

            switch (opcion)
            {
                case 1:

            }
        }
    }*/

    public static void menudos()
    {
        SistemaHotel s1 = new SistemaHotel();
        Scanner sc = new Scanner(System.in);
        int opcion=0;
        String name="";
        String password="";
        char seguir='s';
        do {
            System.out.println("Seleccione modo:");
            System.out.println("1. Administrador");
            System.out.println("2. Cliente");
            System.out.println("3. Recepcionista");
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
                default:
                    System.out.println("Opcion incorrecta");
                    break;
            }
            System.out.println("Desea elegir otra?");
            seguir=sc.next().charAt(0);

        }while (seguir=='s');


    }

    public static void ingresarDatos(String name, String password)
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Ingrese su nombre de usuario:");
        name=sc.nextLine();

        System.out.println("Ingrese contrasena:");
        password=sc.nextLine();
    }
}