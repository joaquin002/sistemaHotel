package Clase;

import Excepcion.DuplicadoEx;
import Excepcion.NoRegistradoEx;

import java.util.ArrayList;
import java.util.HashSet;

public class Administracion extends Usuario {
    private Registro<Hotel>hoteles;
    private Registro<Recepcionista>recepcionistas;

    //constructor para usuario
    public Administracion(String nombreUsuario, String contrasenia) {
        super(nombreUsuario, contrasenia, "Administrador");
        this.hoteles = new Registro<>();
        this.recepcionistas = new Registro<>();
    }

    public Registro<Hotel> getHoteles() {
        return hoteles;
    }

    public Registro<Recepcionista> getRecepcionistas() {
        return recepcionistas;
    }
    //hotel
    public void cargarHotel(int id, String nombre, String direccion)throws DuplicadoEx {
        Hotel aux = new Hotel(id, nombre, direccion);
        for (Hotel h : this.hoteles.getLista()) {
            if (h.getIdHotel() == aux.getIdHotel()) {
                throw new DuplicadoEx("Hotel existente");
            }
        }
        this.hoteles.agregar(aux);
    }
    public void elimianrHotel(int idBuscado){
        this.hoteles.eliminar(hoteles.buscar(idBuscado));
    }
    public String mostrarHotel(int idBuscado){
        return this.hoteles.mostrarPorId(idBuscado);
    }
    //recepcion
    public void cargarRecepcionista(int id, int idHotel) throws DuplicadoEx, NoRegistradoEx {
        Hotel aux = null;
        for (Hotel h : this.hoteles.getLista()) {
            if (h.getIdHotel() == idHotel) {
                aux = h;
                break;
            }
        if (aux == null){
            throw new NoRegistradoEx("no existe el hotel buscado");
        }
        for (Recepcionista r : this.recepcionistas.getLista()) {
            if (r.getIdHotel()==idHotel) {
                throw new DuplicadoEx("ya existe un recepcionista asociado a este hotel");
            }
        }
        this.recepcionistas.agregar(new Recepcionista(id,aux));

        }
    }
    public void eliminarRecepcionista(int idRecepcionista){
        this.recepcionistas.eliminar(recepcionistas.buscar(idRecepcionista));
    }
    public String mostrarRecepcionista(int idRecepcionista){
        return this.recepcionistas.mostrarPorId(idRecepcionista);
    }

}
