package Clase;

import Enums.MetodoPago;
import Enums.ServicioEsepcialDeluxe;
import Enums.ServicioEspecialSuite;
import Excepcion.DuplicadoEx;
import Excepcion.NoRegistradoEx;
import Excepcion.UsuarioNoEncontradoEx;

import java.util.ArrayList;

public class SistemaHotel {
    private ArrayList <Usuario> usuarios;
    private Administracion admin;
    private Recepcionista recepcionista; //un único recepcionista
    private Hotel hotel; //para que no lo cargue administrador

    public SistemaHotel() {
        this.usuarios = new ArrayList<>();
        this.hotel=new Hotel(1, "Hotel BellaVista ", "Avenida Siempre Viva 742"); //crea el hotel
    }

    public Administracion registrarAdministrador(String nombreUsuario, String contrasenia){
        this.admin = new Administracion(nombreUsuario, contrasenia);
        //asignar el hotel creado al administrador
        this.admin.setHotel(this.hotel);
        return this.admin;
    }

    public Recepcionista registrarRecepcionista(int id, String nombreUsuario, String contrasenia) {
        Recepcionista r1 = new Recepcionista(id, nombreUsuario, contrasenia, this.hotel);
        this.recepcionista=r1;
        return this.recepcionista;
    }

    public void registrarUsuario(int id, String nombreUsuario, String contrasenia, int opcion){

        //verificar si existe el usuario
        for (Usuario u : usuarios){
            if (u.getNombreUsuario().equals(nombreUsuario)){
                System.out.println("Error: ya existe un usuario con ese nombre");
                return;
            }
        }
        Usuario nuevo = null;
        switch (opcion) {
            case 1: // Administrador
                nuevo=registrarAdministrador(nombreUsuario, contrasenia);
                break;
            case 2: // Cliente
                nuevo=new Cliente(nombreUsuario, contrasenia);
                break;
            case 3: // Recepcionista
                nuevo=registrarRecepcionista(id, nombreUsuario, contrasenia);
                break;
            default:
                System.out.println("Opción inválida. No se registró ningún usuario.");
                break;
        }
        usuarios.add(nuevo);
        System.out.println("Usuario registrado con éxito");
    }

    public String iniciarSesion(String nombreUsuario, String contrasenia) throws UsuarioNoEncontradoEx {
        for (Usuario u: usuarios){
            if (u.validarUsuario(nombreUsuario, contrasenia)){
                return u.getTipo();
            }
        }
        throw new UsuarioNoEncontradoEx("usuario o contraseña incorrecta");
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

    public String mostrarHotel()
    {
        String rta="";
        if (this.hotel==null){
            rta="No existe el hotel";
        }
        rta=this.hotel.toString();
        return rta;
    }

    public void cargarRecepcionista(int id) {
        try {
            admin.cargarRecepcionista(id);
        } catch (DuplicadoEx e) {
            System.out.println(e.getMessage());
        }
    }

    public String verRecepcionista(int id) {
        String rta = "";
        try {
            rta=admin.mostrarRecepcionista(id);
        } catch (NoRegistradoEx e) {
            System.out.println(e.getMessage());
        }
        return rta;
    }

    public boolean eliminarRecepcionista(){
        return admin.eliminarRecepcionista();
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
        try {
            rta=admin.getHotel().mostrarHabitacion(idHabitacion);
        } catch (NoRegistradoEx e) {
            System.out.println(e.getMessage());
        }
        return rta;
    }

    public String mostrarHabitaciones(){
        String rta="";
        if (this.hotel==null){
            rta="No existe el hotel";
        }
        rta=this.hotel.mostrarTodasLasHabitaciones();
        return rta;
    }

    //recepcionista metodos
    public void checkIn(int dniCliente, int idHabitacion, String fechaEstadia, String nombre, String domicilio, MetodoPago metodoPago){
        try {
            recepcionista.checkIn(dniCliente, idHabitacion, fechaEstadia, nombre, domicilio, metodoPago);
        }catch (NoRegistradoEx e){
            System.out.println(e.getMessage());
        }
    }

    public void checkOut(int idReserva, int dniCliente, String fecha){
        try {
            recepcionista.checkOut(idReserva, dniCliente, fecha);
        } catch (NoRegistradoEx e) {
            System.out.println(e.getMessage());
        }
    }
}
