package Clase;

import Excepcion.DuplicadoEx;
import Excepcion.NoRegistradoEx;
import Excepcion.UsuarioNoEncontradoEx;

import java.util.ArrayList;

public class SistemaHotel {
    private ArrayList <Usuario> usuarios;

    public SistemaHotel() {
        this.usuarios = new ArrayList<>();
    }

    public void registrarUsuario(int id,String nombreUsuario, String contrasenia, int opcion){
       Usuario nuevo=null;
        switch (opcion){
            case 1: //administrador
                nuevo= new Administracion(nombreUsuario, contrasenia);
                break;
            case 2: //cliente
                nuevo= new Cliente(nombreUsuario, contrasenia);
                break;
            case 3: // recepcionista
                nuevo= new Recepcionista(id,nombreUsuario, contrasenia);
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

   /* public String tipoToString()
    {
        String rta="";
        int opcion=0;
         switch (opcion)
        {
            case 1:
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
    }*/

    public void cargarHotel(int id, String nombre, String direccion) throws DuplicadoEx
    {
        for(Usuario u: usuarios)
        {
            if(u instanceof Administracion)
            {
                ((Administracion) u).cargarHotel(id,nombre,direccion);
            }
            else {
                throw new DuplicadoEx("Hotel existente");
            }
        }

    }

    public String mostrarHoteles(int id)
    {
        String rta="";
        for(Usuario u: usuarios)
            if (u instanceof Administracion)
            {
                rta=((Administracion) u).mostrarHotel(id);
            }
        return rta;
    }

    public void cargarRecepcionista(int id, int idHotel)
    {
        for(Usuario u: usuarios)
        {

            if(u instanceof Administracion)
            {
                try
                {
                    ((Administracion) u).cargarRecepcionista(id,idHotel);

                }catch (DuplicadoEx e)
                {
                    System.out.println(e.getMessage());
                }
                catch (NoRegistradoEx e)
                {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public String verRecepcionista(int id)
    {
        String rta="";
        for(Usuario u: usuarios)
        {
            if(u instanceof Administracion)
            {
               rta= ((Administracion) u).mostrarRecepcionista(id);
            }
        }
        return rta;
    }

    public void cargarHabitacionEstandar(int id, int precio, String descripcion,String servicios, int personasPermitidas, boolean estado, int idHotel)
    {

        for(Usuario u: usuarios)
        {
            if(u instanceof Administracion)
            {
                Hotel h1=((Administracion) u).buscarHotel(idHotel);
                h1.agregarHabitacionEstandar(id,precio,descripcion,servicios,personasPermitidas,estado);
            }
        }

    }

    public String mostrarHabitacion(int idHabitacion, int idHotel)
    {
        String rta="";
        for(Usuario u: usuarios)
        {
            if(u instanceof Administracion)
            {
              rta=((Administracion) u).mostrarHabitacionHotel(idHabitacion, idHotel);
            }
        }
        return rta;
    }
}
