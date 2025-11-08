package Clase;

import Enums.ServicioEsepcialDeluxe;
import Enums.ServicioEspecialSuite;
import Excepcion.DuplicadoEx;
import Excepcion.NoRegistradoEx;
import Excepcion.UsuarioNoEncontradoEx;

import java.util.ArrayList;

public class SistemaHotel {
    private ArrayList <Usuario> usuarios;
    private Administracion admin;

    public SistemaHotel() {
        this.usuarios = new ArrayList<>();
    }

    public void registrarAdministrador(String nombreUsuario, String contrasenia){
        this.admin = new Administracion(nombreUsuario, contrasenia);
        usuarios.add(admin);
    }

    public void registrarUsuario(int id, String nombreUsuario, String contrasenia, int opcion){
       Usuario nuevo=null;
        switch (opcion){
            case 1: //administrador
                registrarAdministrador(nombreUsuario, contrasenia);
                System.out.println("usuario registrado con exito");
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
        if (nuevo!=null){
            usuarios.add(nuevo);
            System.out.println("usuario registrado con exito");
        }

    }

    public String iniciarSesion(String nombreUsuario, String contrasenia) throws UsuarioNoEncontradoEx {
        for (Usuario u: usuarios){
            if (u.validarUsuario(nombreUsuario, contrasenia)){
                return u.getTipo();
            }
        }
        throw new UsuarioNoEncontradoEx("usuario o contraseña incorrecta");
    }


//REVISAR
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

    private void cargarHotel2(int id, String nombre, String direccion) {

    }

    //PREGUNTA!!!! para que queremos eliminar un hotel?
    public void eliminarHotel(){
        for (Usuario u: usuarios){
            if (u instanceof Administracion){
                ((Administracion) u).eliminarHotel();
                break;
            }
        }
    }
    public String mostrarHotel(int id)
    {
        String rta="";
        for(Usuario u: usuarios)
            if (u instanceof Administracion)
            {
                rta=((Administracion) u).mostrarHotel();
            }
        return rta;
    }
    public void cargarRecepcionista(int id) {
        for (Usuario u : usuarios) {

            if (u instanceof Administracion) {
                try {
                    ((Administracion) u).cargarRecepcionista(id);
                } catch (DuplicadoEx e) {
                    System.out.println(e.getMessage());
                } catch (NoRegistradoEx e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
    public String verRecepcionista(int id) {
        String rta = "";
        for (Usuario u : usuarios) {
            if (u instanceof Administracion) {
                try {
                    rta = ((Administracion) u).mostrarRecepcionista(id);
                } catch (NoRegistradoEx e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return rta;
    }

    //cargar y muestra habitaciones
    public void cargarHabitacionEstandar(int id, int precio, String descripcion, String servicios, int personasPermitidas, boolean estado) {
      try {
          admin.agregarHabitacionEstandar(id, precio, descripcion, servicios, personasPermitidas, estado);
      }catch (NoRegistradoEx e){
          System.out.println(e.getMessage());
      }
    }

    public void cargarHabitacionSuite(int id, int precio, String descripcion, String servicios, int personasPermitidas, ServicioEspecialSuite especialSuite, boolean disponible) {
       try {
           admin.agregarHabitacionSuiete(id, precio, descripcion, servicios, personasPermitidas, especialSuite, disponible);
       }catch (NoRegistradoEx e){
           System.out.println(e.getMessage());
       }
    }

    public void cargarHabitacionDeluxe(int id, int precio, String descripcion, String servicios, int personasPermitidas, ServicioEsepcialDeluxe servicioEsepcialDeluxe, boolean disponible) {
       try {
           admin.agregarHabitacionDeluxe(id, precio, descripcion, servicios, personasPermitidas, servicioEsepcialDeluxe, disponible);
       }catch (NoRegistradoEx e){
           System.out.println(e.getMessage());
       }
    }
    public void eliminarHabitacion(int idHabitacion){
        admin.eliminarHabitacion(idHabitacion);
    }

    public String mostrarHabitacion(int idHabitacion) {
        String rta = "";
        for (Usuario u : usuarios) {
            if (u instanceof Administracion) {
                try {
                    rta = ((Administracion) u).getHotel().mostrarHabitacion(idHabitacion);
                    break;
                } catch (NoRegistradoEx e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return rta;
    }
}
