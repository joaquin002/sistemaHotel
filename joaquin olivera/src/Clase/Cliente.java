package Clase;

import Enums.MetodoPago;
import Excepcion.NoRegistradoEx;
import Interfaces.Identificable;

import java.util.ArrayList;

public class Cliente extends Usuario implements Identificable {

    private String nombre;
    private int dni;
    private String domicilio;
    private MetodoPago metodoPago;
    private ArrayList <Historial> historial;
    private Reserva reserva;
    private Hotel hotel; //para asociar al cliente cuando hace la reserva

    //constructor normal:
    public Cliente(String nombreUsuario, String contrasenia, String nombre, int dni, String domicilio, MetodoPago metodoPago, int idReserva, int dniCliente, int idRecepcionista, String fecha, int idHabitacion) {
        super(nombreUsuario, contrasenia, "Cliente");
        this.nombre = nombre;
        this.dni = dni;
        this.domicilio = domicilio;
        this.metodoPago = metodoPago;
        this.reserva=new Reserva(idReserva, dniCliente, fecha, idHabitacion);
        this.historial = new ArrayList<>();
    }

    public Cliente(String nombre, int dni, String domicilio, MetodoPago metodoPago){
        this.nombre =  nombre;
        this.dni = dni;
        this.domicilio = domicilio;
        this.metodoPago = metodoPago;
        this.historial = new ArrayList<>();
    }


    //constructor para usuario
    public Cliente(String nombreUsuario, String contrasenia) {
        super(nombreUsuario, contrasenia,"Cliente");
    }

    public void guardarHistorial(int dniCliente, String fechaEstadia){
        Historial h1=new Historial(dniCliente,fechaEstadia);
        this.historial.add(h1);
    }

    public void agregarReserva(Reserva reserva){
        this.reserva=reserva;
    }

    @Override
    public int getIdBuscado() {
        return this.dni;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDni() {
        return dni;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public ArrayList<Historial> getHistorial() {
        return historial;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void hacerReserva(int idHabitacion, String fecha) throws NoRegistradoEx {
        Habitacion h1=hotel.buscarHabitacion(idHabitacion);
        //verifica que este la habitacion buscada
        if (h1==null){
            throw new NoRegistradoEx("no se encontro la habitacion con id: "+idHabitacion);
        }
        //verifica que este disponible
        if (!h1.isDisponible()){
            throw new NoRegistradoEx("la habitacion seleccionada no esta disponible");
        }

        //el cliente crea la reserva
        Reserva nuevaReserva=new Reserva(this.getDni(), fecha, idHabitacion);

        //guarda la reserva
        this.reserva=nuevaReserva;

        //marcar habitación como ocupada
        h1.setDisponible(false);

        System.out.println("reserva realizada exitosamente para la habitacion " + idHabitacion + " en la fecha " + fecha);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", dni=" + dni +
                ", domicilio='" + domicilio + '\'' +
                ", metodoPago=" + metodoPago +
                ", historial=" + historial +
                ", reserva=" + reserva +
                '}';
    }
}
