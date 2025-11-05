package Clase;

import Excepcion.UsuarioNoEncontradoEx;

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
                nuevo= new Cliente(nombreUsuario, contrasenia);
                break;
            case 3: // recepcionista
                nuevo= new Recepcionista(nombreUsuario, contrasenia);
                break;
            default:
                System.out.println("opcion invalida");
                break;
        }
        usuarios.add(nuevo);
        System.out.println("usuario registrado con exito");
    }

    public String iniciarSesion(String nombreUsuario, String contrasenia) throws UsuarioNoEncontradoEx {
        for (Usuario u: usuarios){
            if (u.validarUsuario(nombreUsuario, contrasenia)){
                return u.getTipo();
            }
        }
        throw new UsuarioNoEncontradoEx("usuario o contraseña incorrecta");
    }

    public String tipoToString(String tipo)
    {
        String rta="";
         switch (tipo)
        {
            case 1 :
                rta="Administrador";
                break;
            case 2:
                rta="Cliente";
                break;
            case 3:
                rta="Recepcionista";
                break;
            default:
                rta="Desconocido";
                break;
        }
        return rta;
    }
}
