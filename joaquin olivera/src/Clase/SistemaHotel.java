package Clase;

import Enums.ServicioEsepcialDeluxe;
import Enums.ServicioEspecialSuite;
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


    public void cargarHotel(int id, String nombre, String direccion) {
        for (Usuario u : usuarios) {
            if (u instanceof Administracion) {
                try {
                    ((Administracion) u).cargarHotel(id, nombre, direccion);
                    break;
                } catch (DuplicadoEx e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public String mostrarHoteles(int id)
    {
        String rta="";
        for(Usuario u: usuarios)
            if (u instanceof Administracion)
            {
                rta=((Administracion) u).mostrarHotel();
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
                    ((Administracion) u).cargarRecepcionista(id);

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
    //cargar y muestra habitaciones
    public void cargarHabitacionEstandar(int id, int precio, String descripcion,String servicios, int personasPermitidas, boolean estado)
    {
        for (Usuario u :  usuarios){
            if (u instanceof Administracion) {
                ((Administracion) u).agregarHabitacionEstandar(id, precio, descripcion, servicios, personasPermitidas, estado);
                break;
            }
        }
    }
    public void cargarHabitacionSuite(int id, int precio, String descripcion, String servicios, int personasPermitidas, ServicioEspecialSuite especialSuite, boolean disponible){
        for (Usuario u :  usuarios){
            if (u instanceof Administracion) {
                ((Administracion) u).agregarHabitacionSuiete(id, precio, descripcion, servicios, personasPermitidas, especialSuite, disponible);
                break;
            }
        }
    }
    public void cargarHabitacionDeluxe(int id, int precio, String descripcion, String servicios, int personasPermitidas, ServicioEsepcialDeluxe servicioEsepcialDeluxe, boolean disponible){
        for (Usuario u :  usuarios){
            if (u instanceof Administracion) {
                ((Administracion) u).agregarHabitacionDeluxe(id, precio, descripcion, servicios, personasPermitidas, servicioEsepcialDeluxe, disponible);
                break;
            }
        }
    }
    public void eliminarHabitacion(int idHabitacion){
        for (Usuario u :  usuarios){
            if (u instanceof Administracion) {
                ((Administracion) u).eliminarHabitacion(idHabitacion);
                break;
            }
        }
    }

    public String mostrarHabitacion(int idHabitacion)
    {
        String rta="";
        for(Usuario u: usuarios)
        {
            if(u instanceof Administracion)
            {
              rta=((Administracion) u).getHotel().mostrarHabitacion(idHabitacion);
              break;
            }
        }
        return rta;
    }
}
