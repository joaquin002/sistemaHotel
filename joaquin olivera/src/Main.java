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


    }

}