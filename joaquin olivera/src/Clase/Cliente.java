package Clase;

import Enums.MetodoPago;
import Excepcion.NoRegistradoException;
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

    public String hacerReserva(String nombre, int dni, String domicilio, MetodoPago metodoPago, int idHabitacion, String fecha, Recepcionista recepcionista) throws NoRegistradoException {
        if (hotel==null){
            throw new NoRegistradoException("El cliente no esta asociado a ningun hotel");
        }

        //guarda o actualiza datos si no estaban
        if (this.nombre==null || this.nombre.isEmpty()){
            this.nombre=nombre;
        }

        if (this.dni==0){
            this.dni=dni;
        }

        if (this.domicilio==null || this.domicilio.isEmpty()){
            this.domicilio=domicilio;
        }

        if (this.metodoPago==null){
            this.metodoPago=metodoPago;
        }

        Habitacion h1=hotel.buscarHabitacion(idHabitacion);
        //verifica que este la habitacion buscada
        if (h1==null){
            throw new NoRegistradoException("no se encontro la habitacion con id: "+idHabitacion);
        }
        //verifica que este disponible
        if (!h1.isDisponible()){
            throw new NoRegistradoException("la habitacion seleccionada no esta disponible");
        }

        //el cliente crea la reserva
        Reserva nuevaReserva=new Reserva(this.getDni(), fecha, idHabitacion);
        //guarda la reserva
        this.reserva=nuevaReserva;
        this.guardarHistorial(this.getDni(), fecha);

        //marcar habitación como ocupada
        h1.setDisponible(false);

        recepcionista.guardarReserva(nuevaReserva);

        return "Reserva realizada exitosamente para la habitación " + idHabitacion + " en la fecha " + fecha;
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
