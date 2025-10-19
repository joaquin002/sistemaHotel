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
            case 2: //conserje
                nuevo=new Conserje(nombreUsuario, contrasenia);
                break;
                //falta
            default:
                System.out.println("opcion invalida");
                break;
        }
        usuarios.add(nuevo);
        System.out.println("usuario registrado con exito");
    }

    public String iniciarSesion(String nombreUsuario, String contrasenia){
        String rta="";
        for (Usuario u: usuarios){
            if (u.getNombreUsuario().equals(nombreUsuario) && u.getContrasenia().equals(contrasenia)){
                rta="inicio sesion correctamente";
            }else {
                rta="nombre de usuario o contraseña incorrecto";
            }
        }
        return rta;
    }
}
