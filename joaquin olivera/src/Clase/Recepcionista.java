package Clase;

import java.util.ArrayList;

public class Recepcionista extends Usuario {
    private int id;
    private int idHotel;
    private ArrayList<Cliente> clientes;
    private Registro<Reserva> reservas;
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
        this.reservas = new Registro<>();
        this.puntos = new ArrayList<>();
        this.registroVisitas = new ArrayList<>();
    }
/*  
    public String checkIn(Cliente cliente, Habitacion habitacion){
        if(!habitacion.getEstado())
        {
            return "La habitacion no esta disponible";
        }
        habitacion.
    }
*/
    public void cargarReserva(int idReserva,int dniCliente, int idRecepcionista, String fecha)
    {
        Reserva reserva=new Reserva(idReserva,dniCliente,idRecepcionista,fecha);
        reservas.agregar(reserva);
    }

    public void checkOut() {}
}
