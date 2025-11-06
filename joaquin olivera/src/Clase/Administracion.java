package Clase;

import Excepcion.DuplicadoEx;
import Excepcion.NoRegistradoEx;

import java.util.ArrayList;
import java.util.HashSet;

public class Administracion extends Usuario {
    private Hotel hotel;
    private Registro<Recepcionista>recepcionistas;

    //constructor para usuario
    public Administracion(String nombreUsuario, String contrasenia) {
        super(nombreUsuario, contrasenia, "Administrador");
        this.recepcionistas = new Registro<>();
    }

    public Registro<Recepcionista> getRecepcionistas() {
        return recepcionistas;
    }
    //hotel
    public void cargarHotel(int id, String nombre, String direccion)throws DuplicadoEx {
        if(this.hotel!=null){
            throw new DuplicadoEx("Ya existe un hotel cargado en el sistema");
        }
        this.hotel = new Hotel(id, nombre, direccion);
        System.out.println("Hotel cargado con exito:" +nombre);
    }

    public String mostrarHotel(){
        if(this.hotel==null){
            return "No hay hotel cargado";
        }
        return this.hotel.toString();
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
        this.recepcionistas.agregar(new Recepcionista(id, this.hotel));
    }

    public void eliminarRecepcionista(int idRecepcionista){
        this.recepcionistas.eliminar(recepcionistas.buscar(idRecepcionista));
    }
    public String mostrarRecepcionista(int idRecepcionista){
        return this.recepcionistas.mostrarPorId(idRecepcionista);
    }


    public String mostrarHabitacionHotel(int idBuscado)
    {
        return this.hotel.mostrarHabitacion(idBuscado);
    }

}
