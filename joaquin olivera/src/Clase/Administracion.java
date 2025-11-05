package Clase;

import java.util.ArrayList;
import java.util.HashSet;

public class Administracion extends Usuario {
    private Registro<Hotel>hoteles;

    //constructor para usuario
    public Administracion(String nombreUsuario, String contrasenia) {
        super(nombreUsuario, contrasenia, 1);
        this.hoteles = new Registro<>();
    }

    public Registro<Hotel> getHoteles() {
        return hoteles;
    }

    public void cargarHotel(int id, String nombre, String direccion, int recaudacion){
        this.hoteles.agregar(new Hotel(id, nombre, direccion, recaudacion));
    }
    public void elimianrHotel(int idBuscado){
        this.hoteles.eliminar(hoteles.buscar(idBuscado));
    }
    public String mostrarHotel(int idBuscado){
        return this.hoteles.mostrarPorId(idBuscado);
    }

}
