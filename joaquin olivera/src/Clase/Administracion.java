package Clase;

import Enums.ServicioEsepcialDeluxe;
import Enums.ServicioEspecialSuite;
import Excepcion.DuplicadoEx;
import Excepcion.NoRegistradoEx;

import java.util.ArrayList;
import java.util.HashSet;

public class Administracion extends Usuario {
    private Hotel hotel;
    private Registro<Recepcionista> recepcionistas;

    //constructor para usuario
    public Administracion(String nombreUsuario, String contrasenia) {
        super(nombreUsuario, contrasenia, "Administrador");
        this.recepcionistas = new Registro<>();
    }

    public Registro<Recepcionista> getRecepcionistas() {
        return recepcionistas;
    }

    public Hotel getHotel() {
        return hotel;
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
    public void agregarHabitacionEstandar(int id, int precio, String descripcion,String servicios, int personasPermitidas, boolean estado) throws NoRegistradoEx{
        //verifica si hay hotel
        if(this.hotel==null){
            throw new NoRegistradoEx("No hay hotel cargado");
        }

        this.hotel.agregarHabitacionEstandar(id, precio, descripcion, servicios, personasPermitidas, estado);
    }
    public void agregarHabitacionSuiete(int id, int precio, String descripcion, String servicios, int personasPermitidas, ServicioEspecialSuite especialSuite, boolean disponible)throws NoRegistradoEx{
        //verifica si hay hotel
        if(this.hotel==null){
            throw new NoRegistradoEx("No hay hotel cargado");
        }
        this.hotel.agregarSuite(id, precio, descripcion, servicios, personasPermitidas, especialSuite, disponible);
    }
    public void agregarHabitacionDeluxe(int id, int precio, String descripcion, String servicios, int personasPermitidas, ServicioEsepcialDeluxe servicioEsepcialDeluxe, boolean disponible)throws NoRegistradoEx{
        //verifica si hay hotel
        if(this.hotel==null){
            throw new NoRegistradoEx("No hay hotel cargado");
        }
        this.hotel.agregarDeluxe(id, precio, descripcion, servicios, personasPermitidas, servicioEsepcialDeluxe, disponible);
    }
    public void eliminarHabitacion(int idHabitacion){
        try{
            this.hotel.elimarHabitacion(idHabitacion);
        }catch (NoRegistradoEx e){
            System.out.println(e.getMessage());
        }
    }

    //recepcion
    public void cargarRecepcionista(int id) throws DuplicadoEx, NoRegistradoEx {
       if(this.hotel==null)
       {
           throw new NoRegistradoEx("Primero debe cargarse un hotel");
       }
        for (Recepcionista recepcionista : this.recepcionistas.getLista())
        {
            if(recepcionista.getIdBuscado()==id)
            {
                throw new DuplicadoEx("Ya existe un recepcionista con ese ID");
            }
        }
        Recepcionista r1=new Recepcionista(id,this.hotel);
        this.recepcionistas.agregar(r1);
    }

    public void eliminarRecepcionista(int idRecepcionista){
        this.recepcionistas.eliminar(recepcionistas.buscar(idRecepcionista));
    }
    public String mostrarRecepcionista(int idRecepcionista) throws NoRegistradoEx{
        if (this.recepcionistas.getLista()==null){
            throw new NoRegistradoEx("No hay recepcionista registrado");
        }
        return this.recepcionistas.mostrarPorId(idRecepcionista);
    }

}
