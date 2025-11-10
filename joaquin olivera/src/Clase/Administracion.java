package Clase;

import Enums.ServicioEsepcialDeluxe;
import Enums.ServicioEspecialSuite;
import Excepcion.DuplicadoEx;
import Excepcion.NoRegistradoEx;

import java.util.ArrayList;
import java.util.HashSet;

public class Administracion extends Usuario {
    private Hotel hotel;
    private Recepcionista recepcionista;

    //constructor para usuario
    public Administracion(String nombreUsuario, String contrasenia) {
        super(nombreUsuario, contrasenia, "Administrador");
        this.recepcionista=recepcionista;
    }


    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    //hotel no va
    public void cargarHotel(int id, String nombre, String direccion)throws DuplicadoEx {
        if(this.hotel!=null){
            throw new DuplicadoEx("Ya existe un hotel cargado en el sistema");
        }
        this.hotel = new Hotel(id, nombre, direccion);
        System.out.println("Hotel cargado con exito:" +nombre);
    }
    //metodo modificar hotel -hacer-
    public void eliminarHotel(){//fijarse si está bien
        this.hotel = null;
        System.out.println("Hotel eliminado con exito");
    }

    //no va
    public String mostrarHotel(){
        if(this.hotel==null){
            return "No hay hotel cargado";
        }
        return this.hotel.toString();
    }

    //habitaciones
    public void agregarHabitacionEstandar( int precio, String descripcion,String servicios, int personasPermitidas, boolean estado) throws NoRegistradoEx{
        //verifica si hay hotel
        if(this.hotel==null){
            throw new NoRegistradoEx("No hay hotel cargado");
        }

        this.hotel.agregarHabitacionEstandar( precio, descripcion, servicios, personasPermitidas, estado);
    }
    public void agregarHabitacionSuiete( int precio, String descripcion, String servicios, int personasPermitidas, ServicioEspecialSuite especialSuite, boolean disponible)throws NoRegistradoEx{
        //verifica si hay hotel
        if(this.hotel==null){
            throw new NoRegistradoEx("No hay hotel cargado");
        }
        this.hotel.agregarSuite( precio, descripcion, servicios, personasPermitidas, especialSuite, disponible);
    }
    public void agregarHabitacionDeluxe( int precio, String descripcion, String servicios, int personasPermitidas, ServicioEsepcialDeluxe servicioEsepcialDeluxe, boolean disponible)throws NoRegistradoEx{
        //verifica si hay hotel
        if(this.hotel==null){
            throw new NoRegistradoEx("No hay hotel cargado");
        }
        this.hotel.agregarDeluxe( precio, descripcion, servicios, personasPermitidas, servicioEsepcialDeluxe, disponible);
    }
    public void eliminarHabitacion(int idHabitacion){
        try{
            this.hotel.elimarHabitacion(idHabitacion);
        }catch (NoRegistradoEx e){
            System.out.println(e.getMessage());
        }
    }

    //recepcion
    public void cargarRecepcionista(int id) throws DuplicadoEx {
        if (this.recepcionista!=null){
            throw new DuplicadoEx("ya existe un recepcionista registrado");
        }
        this.recepcionista=new Recepcionista(id, this.hotel);
    }

    public boolean eliminarRecepcionista(){
       boolean encontrado=false;
        if (this.recepcionista!=null){
            this.recepcionista=null;
            encontrado=true;
        }
        return encontrado;
    }

    public String mostrarRecepcionista(int idRecepcionista) throws NoRegistradoEx{
        if (this.recepcionista==null){
            throw new NoRegistradoEx("No hay recepcionista registrado");
        }
        return this.recepcionista.toString();
    }

}
