package Clase;

import Enums.MetodoPago;
import Excepcion.NoRegistradoException;
import Interfaces.Identificable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    public Cliente(String nombreUsuario, String contrasenia, String nombre, int dni, String domicilio, MetodoPago metodoPago, int idReserva, int dniCliente, String fechaInicio, String fechaFinalizacion, int idHabitacion) {
        super(nombreUsuario, contrasenia, "Cliente");
        this.nombre = nombre;
        this.dni = dni;
        this.domicilio = domicilio;
        this.metodoPago = metodoPago;
        this.reserva=new Reserva(idReserva, dniCliente, fechaInicio, fechaFinalizacion, idHabitacion);
        this.historial = new ArrayList<>();
    }

    public Cliente(String nombre, int dni, String domicilio, MetodoPago metodoPago){
        this.nombre =  nombre;
        this.dni = dni;
        this.domicilio = domicilio;
        this.metodoPago = metodoPago;
        this.historial = new ArrayList<>();
    }

    public Cliente(String nombreUsuario, String contrasenia, String tipo, String nombre, int dni, String domicilio, MetodoPago metodoPago, Hotel hotel) {
        super(nombreUsuario, contrasenia, tipo);
        this.nombre = nombre;
        this.dni = dni;
        this.domicilio = domicilio;
        this.metodoPago = metodoPago;
        this.hotel = hotel;
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

    public String hacerReserva(String nombre, int dni, String domicilio, MetodoPago metodoPago, int idHabitacion, Recepcionista recepcionista, String fechaCheckIn, String fechaCheckOut) throws NoRegistradoException {
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

        // === VALIDACIÓN DE FECHAS ===
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate nuevaCheckIn = LocalDate.parse(fechaCheckIn, formatter);
        LocalDate nuevaCheckOut = LocalDate.parse(fechaCheckOut, formatter);

        if (nuevaCheckOut.isBefore(nuevaCheckIn)) {
            throw new NoRegistradoException("La fecha de salida no puede ser anterior a la de ingreso.");
        }

        // Buscar reservas del recepcionista que correspondan a la misma habitación
        for (Reserva r : recepcionista.getReservas().getLista()) {
            if (r.getIdHabitacion() == idHabitacion) {
                LocalDate existenteCheckIn = LocalDate.parse(r.getFechaInicio(), formatter);
                LocalDate existenteCheckOut = LocalDate.parse(r.getFechaFinalizacion(), formatter);

                // si se solapan las fechas, no puede reservar
                if (nuevaCheckIn.isBefore(existenteCheckOut) && nuevaCheckOut.isAfter(existenteCheckIn)) {
                    throw new NoRegistradoException("La habitación ya está reservada entre " +
                            existenteCheckIn + " y " + existenteCheckOut);
                }
            }
        }


        // El cliente crea la reserva (con check-in y check-out)
        Reserva nuevaReserva = new Reserva(this.getDni(), recepcionista.getIdBuscado(),
                fechaCheckIn, fechaCheckOut, idHabitacion);

        // Guarda la reserva
        this.reserva = nuevaReserva;
        this.guardarHistorial(this.getDni(), fechaCheckIn);

        // Marcar habitación como ocupada (puede volver a disponible luego del check-out)
        h1.setDisponible(false);

        // Guardar la reserva en el registro del recepcionista
        recepcionista.guardarReserva(nuevaReserva);

        return "Reserva realizada exitosamente para la habitación " + idHabitacion +
                " desde " + fechaCheckIn + " hasta " + fechaCheckOut;
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
    public JSONObject toJSON(){
        JSONObject obj=new JSONObject();
        try{
            obj.put("nombre", nombre);
            obj.put("dni", dni);
            obj.put("domicilio", domicilio);
            obj.put("metodoPago", metodoPago);
            obj.put("hotel", hotel.toJSON());
            obj.put("reserva", reserva.toJSON());
            JSONArray historialJson = new JSONArray();
            for (Historial historial : this.historial){
                historialJson.put(historial.toJSON());
            }
            obj.put("historial", historialJson);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return obj;
    }
}
