package Clase;

public class Recepcionista extends Usuario {
    private int idPasajero;
    private int idReserva;

    //para parte de usuario
    public Recepcionista(String nombreUsuario, String contrasenia) {
        super(nombreUsuario, contrasenia);
    }

    public Recepcionista(String nombreUsuario, String contrasenia, int idPasajero, int idReserva) {
        super(nombreUsuario, contrasenia);
        this.idPasajero = idPasajero;
        this.idReserva = idReserva;
    }
}
