package Clase;

import Enums.MetodoPago;
import Excepcion.NoRegistradoException;
import Interfaces.Identificable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;

public class Recepcionista extends Usuario implements Identificable {
    private int id;
    private Registro<Cliente> clientes;
    private Registro<Reserva> reservas;
    private Hotel hotel;

    //para usuario
    public Recepcionista(String nombreUsuario, String contrasenia, Hotel hotel) {
        super(nombreUsuario, contrasenia, "Recepcionista");
        this.id = 1;
        this.hotel = hotel;
        this.clientes=new Registro<>();
        this.reservas=new Registro<>();
    }


    public Recepcionista(JSONObject obj)throws JSONException {
        super(obj);
        this.clientes = new Registro<>();
        this.reservas = new Registro<>();

        this.id = obj.optInt("id",0);

        JSONArray clientes = obj.optJSONArray("clientes");
        if (clientes != null) {
            for(int i = 0; i < clientes.length(); i++){
                JSONObject cliente = clientes.getJSONObject(i);
                this.clientes.agregar(new Cliente(cliente));
            }
        }
        JSONArray reservas = obj.optJSONArray("reservas");
        if (reservas != null) {
            for(int i = 0; i < reservas.length(); i++){
                JSONObject reserva = reservas.getJSONObject(i);
                this.reservas.agregar(new Reserva(reserva));
            }
        }

        JSONObject hotel = obj.optJSONObject("hotel");
        if (hotel != null) {
            this.hotel =  new Hotel(hotel);
        }else {
            this.hotel = null;
        }
    }

    @Override
    public int getIdBuscado() {
        return this.id;
    }

    public Registro<Reserva> getReservas() {
        return reservas;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }


    public void registrarClienteExistente(Cliente c1) {
        c1.setHotel(this.hotel);
        clientes.agregar(c1);
    }


    //busca una reserva
    public Reserva buscarReserva(int id) {
        return this.reservas.buscar(id);
    }

    public Cliente buscarCliente(int dni) {
        return this.clientes.buscar(dni);
    }

    public void guardarReserva(Reserva reserva) {
        reservas.agregar(reserva);
    }

    public void checkIn(int dniCliente, int idReserva) throws NoRegistradoException {

        //verifica si el cliente ya existe
        Cliente c1 = buscarCliente(dniCliente);
        if (c1 == null) {
            throw new NoRegistradoException("No existe el cliente con el dni: "+dniCliente);
        }


        //verificar si ya tiene reserva
        Reserva reservaCliente = buscarReserva(idReserva);
        if (reservaCliente == null) {
            throw new NoRegistradoException("no existe la reserva");
        }

        Habitacion h1 = hotel.buscarHabitacion(reservaCliente.getIdHabitacion());
        if (h1 == null) {
            throw new NoRegistradoException("No se encontró la habitación con id: " + reservaCliente.getIdHabitacion());
        }

        //Marcar como ocupada al hacer el check-in
        h1.setDisponible(false);

        System.out.println(" Check-In realizado con éxito del cliente " + dniCliente +
                " para la reserva " + idReserva +
                ". Fecha de ingreso: " + reservaCliente.getFechaInicio() +
                ", fecha de salida: " + reservaCliente.getFechaFinalizacion());
    }

    public void checkOut(int idReserva, int dniCliente) throws NoRegistradoException {
        Reserva r2 = buscarReserva(idReserva); //buscar la reserva
        if (r2 == null) {
            throw new NoRegistradoException("la reserva con id: " + idReserva + " no esta registrada");
        }

        //buscar habitacion de la reserva
        Habitacion h1 = hotel.buscarHabitacion(r2.getIdHabitacion());
        if (h1 == null) {
            throw new NoRegistradoException("no se encontro la habitacion asociada a la reserva");
        }

        // VALIDAR QUE YA SE HIZO EL CHECK-IN
        if (h1.isDisponible()) {
            throw new NoRegistradoException("No se puede hacer el Check-Out porque la habitación aún no tiene Check-In.");
        }

        //liberar habitacion
        h1.setDisponible(true);

        //sumar recaudacion
        this.hotel.sumarRecaudacion(h1.getPrecio());

        //para que se guarde en el historial
        Cliente c1 = buscarCliente(dniCliente);
        if (c1 == null) {
            throw new NoRegistradoException("no se encontro el cliente con dni: " + dniCliente);
        }



        System.out.println(" Check-Out realizado con éxito de la reserva " + idReserva +
                ". Habitación " + h1.getIdBuscado() + " liberada. " +
                "Fecha de estadía: " + r2.getFechaInicio() + " → " + r2.getFechaFinalizacion());
    }



public String consultarDisponibilidad()
{
    String rta="";
    boolean encontrado=false;
    for (Habitacion h1: hotel.getHabitaciones().getLista())
    {
        if(h1.isDisponible()){
            rta+=h1.toString()+"\n";
            encontrado=true;
        }
    }
    if(!encontrado){
        rta="No hay habitaciones disponibles";
    }
    return rta;
}

    public String verHabitacionesNoDisponiblesPorMotivo(){
        String rta="";
        for (Habitacion h1: hotel.getHabitaciones().getLista()){
            if (!h1.isDisponible()){
                rta+=h1.toString()+"\n";
            }
        }
        return rta;
    }

    public String verHabitacionesOcupadas(){
        String rta="";
        boolean encontrado = false;
        for (Habitacion h1: hotel.getHabitaciones().getLista()){
            if (!h1.isDisponible()){
                for (Reserva r: reservas.getLista()){
                    if (r.getIdHabitacion()==h1.getIdBuscado()){
                        Cliente c=buscarCliente(r.getDniCliente());
                        rta+="Habitacion id: "+h1.getIdBuscado()+"\n";
                        if (c!=null){
                            rta+="Cliente: "+ c.getNombre()+"\n";
                            rta+="Dni: "+c.getDni()+"\n";
                        }else {
                            rta+="Cliente no registrado"+"\n";
                        }
                        rta+="Fecha de reserva: "+r.getFechaInicio()+" fecha de finalizacion: "+r.getFechaFinalizacion() +"\n";
                        encontrado=true;
                    }
                }
            }
            if (encontrado==false){
                rta="No hay habitaciones ocupadas";
            }
        }
        return rta;
    }

    public String mostrar() {
        return super.toString()+"Recepcionista{" +
                "id=" + id +
                ", hotel=" + hotel +
                '}';
    }

    public String mostrarClientes()
        {

        return clientes.mostrar();
        }

        public String mostrarReservas()
        {
            return reservas.mostrar();
        }

    @Override
    public String toString() {
        return "Recepcionista{" +
                "id=" + id +
                ", clientes=" + clientes +
                ", reservas=" + reservas +
                ", hotel=" + hotel +
                '}';
    }

    public JSONObject toJson(){
        JSONObject json = super.toJson();
        try{
            json.put("id", this.id);
            /*
            JSONArray clienteJSON = new JSONArray();
            for (Cliente c:this.clientes.getLista()){
                clienteJSON.put(c.toJSON());
            }
            json.put("clientes", clienteJSON);
            */
            JSONArray reservasJSON = new JSONArray();
            for (Reserva r:reservas.getLista()){
                reservasJSON.put(r.toJSON());
            }
            json.put("reservas", reservasJSON);
            
        }catch (JSONException e){
            e.printStackTrace();
        }
        return json;
    }
}