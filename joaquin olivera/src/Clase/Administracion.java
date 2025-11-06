package Clase;

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

    public void cargarHotel(int id, String nombre, String direccion){
        this.hoteles.agregar(new Hotel(id, nombre, direccion));
    }
    public void elimianrHotel(int idBuscado){
        this.hoteles.eliminar(hoteles.buscar(idBuscado));
    }
    public String mostrarHotel(int idBuscado){
        return this.hoteles.mostrarPorId(idBuscado);
    }
    public void cargarRecepcionista(int id,int idHotel){
        for (Hotel h:this.hoteles.getLista()){
            if(h.getIdHotel()==idHotel){
                this.recepcionistas.agregar(new Recepcionista(id,h));
                break;
            }
        }
    }
    public void eliminarRecepcionista(int idRecepcionista){
        this.recepcionistas.eliminar(recepcionistas.buscar(idRecepcionista));
    }
    public String mostrarRecepcionista(int idRecepcionista){
        return this.recepcionistas.mostrarPorId(idRecepcionista);
    }

}
