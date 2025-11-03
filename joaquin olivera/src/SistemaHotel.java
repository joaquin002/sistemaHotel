import java.util.ArrayList;

public class SistemaHotel {
    private ArrayList <Usuario> usuarios;

    public SistemaHotel() {
        this.usuarios = new ArrayList<>();
    }

    public void registrarUsuario(String nombreUsuario, String contrasenia, int opcion){
       Usuario nuevo=null;
        switch (opcion){
            case 1: //administrador
                nuevo= new Administracion(nombreUsuario, contrasenia);
                break;
            case 2: //cliente

                break;
                //falta
            default:
                System.out.println("opcion invalida");
                break;
        }
        usuarios.add(nuevo);
        System.out.println("usuario registrado con exito");
    }

    public String iniciarSesion(String nombreUsuario, String contrasenia) throws UsuarioNoEncontradoEx{
        for (Usuario u: usuarios){
            if (u.validarUsuario(nombreUsuario, contrasenia)){
                return "inicio sesion correctamente";
            }
        }
        throw new UsuarioNoEncontradoEx("usuario o contraseña incorrecta");
    }
}
