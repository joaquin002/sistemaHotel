package Clase;

import java.util.ArrayList;

public class Recepcionista extends Usuario {
    private int id;
    private int idHotel;
    private ArrayList<Cliente> clientes;
    private ArrayList<Reserva> reservas;
    private ArrayList<Punto> puntos;
    private ArrayList<RegistroVisita> registroVisitas;

    //para parte de usuario
    public Recepcionista(String nombreUsuario, String contrasenia) {
        super(nombreUsuario, contrasenia);
    }

    public Recepcionista(String nombreUsuario, String contrasenia, int id, int idHotel) {
        super(nombreUsuario, contrasenia);
        this.id = id;
        this.idHotel = idHotel;
        this.clientes = new ArrayList<>();
        this.reservas = new ArrayList<>();
        this.puntos = new ArrayList<>();
        this.registroVisitas = new ArrayList<>();
    }
}
