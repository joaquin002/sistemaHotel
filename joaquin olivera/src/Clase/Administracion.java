package Clase;

import java.util.HashSet;

public class Administracion extends Usuario {
    private HashSet<Hotel>hoteles;

    //constructor para usuario
    public Administracion(String nombreUsuario, String contrasenia) {
        super(nombreUsuario, contrasenia);
        this.hoteles = new HashSet<>();
    }

    public HashSet<Hotel> getHoteles() {
        return hoteles;
    }
}
