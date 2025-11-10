package Clase;

import Enums.ServicioEspecialDeluxe;
import Enums.ServicioEspecialSuite;
import Excepcion.DuplicadoEx;
import Excepcion.NoRegistradoEx;

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

    public void setRecepcionista(Recepcionista recepcionista) {
        this.recepcionista = recepcionista;
    }

    //habitaciones
    public void agregarHabitacionEstandar(double precio, String descripcion,String servicios, int personasPermitidas, boolean estado, String motivoNoDisponoble) throws NoRegistradoEx{
        //verifica si hay hotel
        if(this.hotel==null){
            throw new NoRegistradoEx("No hay hotel cargado");
        }

        this.hotel.agregarHabitacionEstandar(precio, descripcion, servicios, personasPermitidas, estado, motivoNoDisponoble);
    }
    public void agregarHabitacionSuiete(double precio, String descripcion, String servicios, int personasPermitidas, ServicioEspecialSuite especialSuite, boolean disponible, String motivoNoDisponible)throws NoRegistradoEx{
        //verifica si hay hotel
        if(this.hotel==null){
            throw new NoRegistradoEx("No hay hotel cargado");
        }
        this.hotel.agregarSuite(precio, descripcion, servicios, personasPermitidas, especialSuite, disponible, motivoNoDisponible);
    }
    public void agregarHabitacionDeluxe(double precio, String descripcion, String servicios, int personasPermitidas, ServicioEspecialDeluxe servicioEspecialDeluxe, boolean disponible, String motivoNoDisponible)throws NoRegistradoEx{
        //verifica si hay hotel
        if(this.hotel==null){
            throw new NoRegistradoEx("No hay hotel cargado");
        }
        this.hotel.agregarDeluxe(precio, descripcion, servicios, personasPermitidas, servicioEspecialDeluxe, disponible, motivoNoDisponible);
    }
    public void eliminarHabitacion(int idHabitacion){
        try{
            this.hotel.elimarHabitacion(idHabitacion);
        }catch (NoRegistradoEx e){
            System.out.println(e.getMessage());
        }
    }

    public boolean eliminarRecepcionista(){
       boolean encontrado=false;
        if (this.recepcionista!=null){
            this.recepcionista=null;
            encontrado=true;
        }
        return encontrado;
    }

    public String mostrarRecepcionista() throws NoRegistradoEx{
        if (this.recepcionista==null){
            throw new NoRegistradoEx("No hay recepcionista registrado");
        }
        return this.recepcionista.mostrar();
    }
}
