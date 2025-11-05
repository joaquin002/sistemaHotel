package Clase;

import Enums.MetodoPago;
import Interfaces.Identificable;

import java.util.ArrayList;

public class Cliente extends Usuario implements Identificable {

    private String nombre;
    private int dni;
    private String domicilio;
    private MetodoPago metodoPago;
    private ArrayList <Historial> historial;
    private Reserva reserva;


    //constructor normal:
    public Cliente(String nombreUsuario, String contrasenia, String nombre, int dni, String domicilio, MetodoPago metodoPago, int idReserva, int dniCliente, int idRecepcionista, String fecha, int idHabitacion) {
        super(nombreUsuario, contrasenia, "Cliente");
        this.nombre = nombre;
        this.dni = dni;
        this.domicilio = domicilio;
        this.metodoPago = metodoPago;
        this.reserva=new Reserva(idReserva, dniCliente, fecha, idHabitacion);
    }

    //constructor para usuario
    public Cliente(String nombreUsuario, String contrasenia) {
        super(nombreUsuario, contrasenia,"Cliente");
    }

    @Override
    public int getIdBuscado() {
        return this.dni;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", dni=" + dni +
                ", domicilio='" + domicilio + '\'' +
                ", metodoPago=" + metodoPago +
                '}';
    }
}
