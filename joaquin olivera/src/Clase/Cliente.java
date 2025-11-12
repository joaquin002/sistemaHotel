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


    public Cliente(String nombre, int dni, String domicilio, MetodoPago metodoPago){
        this.nombre =  nombre;
        this.dni = dni;
        this.domicilio = domicilio;
        this.metodoPago = metodoPago;
        this.historial = new ArrayList<>();
    }

    public Cliente(JSONObject obj) throws JSONException {
        super(obj);
        this.nombre = obj.optString("nombre","");
        this.dni=obj.optInt("dni",0);
        this.domicilio = obj.optString("domicilio","");
        this.metodoPago = obj.optEnum(MetodoPago.class,"metodoPago",null);

        this.historial = new ArrayList<>();
        JSONArray historialArray = obj.optJSONArray("historial");
        if (historialArray != null) {
            for (int i = 0; i < historialArray.length(); i++) {
                JSONObject historialObj = historialArray.getJSONObject(i);
                this.historial.add(new Historial(historialObj));
            }
        }

        JSONObject reservaObj = obj.optJSONObject("reserva");
        if (reservaObj != null) {
            this.reserva = new Reserva(reservaObj);
        }

        JSONObject hotelObj = obj.optJSONObject("hotel");
        if (hotelObj != null) {
            this.hotel = new Hotel(hotelObj);
        }
    }


    //constructor para usuario
    public Cliente(String nombreUsuario, String contrasenia) {
        super(nombreUsuario, contrasenia,"Cliente");
        this.historial=new ArrayList<>();
    }

    public void guardarHistorial(int dniCliente, String fechaInicio, String fechaSalida){
        Historial h1=new Historial(dniCliente, fechaInicio, fechaSalida);
        this.historial.add(h1);
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

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getDomicilio() {
        return domicilio;
    }

    // nuevo metodo de hacer reserva. Pensando bien el tema de las fechas, checkIn y checkOut
    public String hacerReserva(int idHabitacion, Recepcionista recepcionista, String fechaCheckIn, String fechaCheckOut) throws NoRegistradoException {
        if (hotel == null) {
            throw new NoRegistradoException("El cliente no está asociado a ningún hotel");
        }

        if (recepcionista == null) {
            throw new NoRegistradoException("No hay recepcionista registrado");
        }

        Habitacion h1 = hotel.buscarHabitacion(idHabitacion);
        if (h1 == null) {
            throw new NoRegistradoException("No se encontró la habitación con id: " + idHabitacion);
        }

        // Validación de fechas
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate nuevaCheckIn = LocalDate.parse(fechaCheckIn, formatter);
        LocalDate nuevaCheckOut = LocalDate.parse(fechaCheckOut, formatter);

        if (nuevaCheckOut.isBefore(nuevaCheckIn)) {
            throw new NoRegistradoException("La fecha de salida no puede ser anterior a la de ingreso.");
        }

        // Validar disponibilidad según reservas del recepcionista
        for (Reserva r : recepcionista.getReservas().getLista()) {
            if (r.getIdHabitacion() == idHabitacion) {
                LocalDate existenteCheckIn = LocalDate.parse(r.getFechaInicio(), formatter);
                LocalDate existenteCheckOut = LocalDate.parse(r.getFechaFinalizacion(), formatter);

                // si se solapan las fechas, no puede reservar
                if (nuevaCheckIn.isBefore(existenteCheckOut) && nuevaCheckOut.isAfter(existenteCheckIn)) {
                    throw new NoRegistradoException("La habitación ya está reservada entre " + existenteCheckIn + " y " + existenteCheckOut);
                }
            }
        }

        // Crear y guardar la reserva si está libre
        Reserva nuevaReserva = new Reserva(this.getDni(), recepcionista.getIdBuscado(), fechaCheckIn, fechaCheckOut, idHabitacion);

        this.reserva = nuevaReserva;

        recepcionista.guardarReserva(nuevaReserva);
        guardarHistorial(dni, nuevaReserva.getFechaInicio(), nuevaReserva.getFechaFinalizacion());

        return "Reserva realizada exitosamente para la habitación " + idHabitacion + " desde " + fechaCheckIn + " hasta " + fechaCheckOut;
    }

    public String verMisReservas(Recepcionista recepcionista) {
        if (recepcionista == null) {
            return "No hay recepcionista disponible para consultar las reservas.";
        }

        ArrayList<Reserva> reservas = recepcionista.getReservas().getLista();
        String rta = "";
        boolean tieneReservas = false;

        for (Reserva r : reservas) {
            if (r.getDniCliente() == this.getDni()) {
                rta += r.toString() + "\n";
                tieneReservas = true;
            }
        }

        if (!tieneReservas) {
            rta = "No tienes reservas registradas.";
        }

        return rta;
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
        JSONObject obj = super.toJson();
        try{
            obj.put("nombre", this.nombre);
            obj.put("dni", this.dni);
            obj.put("domicilio", this.domicilio);
            obj.put("metodoPago", this.metodoPago);
            obj.put("reserva", this.reserva.toJSON());
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
