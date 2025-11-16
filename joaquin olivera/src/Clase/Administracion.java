package Clase;

import Enums.MotivoNoDisponible;
import Enums.ServicioEspecialDeluxe;
import Enums.ServicioEspecialSuite;
import Excepcion.NoRegistradoException;
import org.json.JSONException;
import org.json.JSONObject;

public class Administracion extends Usuario {
    private Hotel hotel;
    private Recepcionista recepcionista;

    //constructor para usuario
    public Administracion(String nombreUsuario, String contrasenia) {
        super(nombreUsuario, contrasenia, "Administrador");
        this.recepcionista=null;
    }

    public Administracion(JSONObject obj) throws JSONException {
        // Cargar datos básicos del usuario
        super(obj);

        if (obj.has("hotel") && !obj.isNull("hotel")) {
            this.hotel = new Hotel(obj.getJSONObject("hotel"));
        } else {
            this.hotel = null;
        }

        // Cargar el recepcionista si está presente
        if (obj.has("recepcionista") && !obj.isNull("recepcionista")) {
            this.recepcionista = new Recepcionista(obj.getJSONObject("recepcionista"));
        } else {
            this.recepcionista = null;
        }
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setRecepcionista(Recepcionista recepcionista) {
        this.recepcionista = recepcionista;
    }

    //habitaciones
    public void agregarHabitacionEstandar( String descripcion, int personasPermitidas, boolean estado, MotivoNoDisponible motivoNoDisponoble) throws NoRegistradoException {
        //verifica si hay hotel
        if(this.hotel==null){
            throw new NoRegistradoException("No hay hotel cargado");
        }

        this.hotel.agregarHabitacionEstandar( descripcion, personasPermitidas, estado, motivoNoDisponoble);
    }
    public void agregarHabitacionSuite( String descripcion, int personasPermitidas, ServicioEspecialSuite especialSuite, boolean disponible, MotivoNoDisponible motivoNoDisponible)throws NoRegistradoException {
        //verifica si hay hotel
        if(this.hotel==null){
            throw new NoRegistradoException("No hay hotel cargado");
        }
        this.hotel.agregarSuite( descripcion, personasPermitidas, especialSuite, disponible, motivoNoDisponible);
    }
    public void agregarHabitacionDeluxe( String descripcion, int personasPermitidas, ServicioEspecialDeluxe servicioEspecialDeluxe, boolean disponible, MotivoNoDisponible motivoNoDisponible)throws NoRegistradoException {
        //verifica si hay hotel
        if(this.hotel==null){
            throw new NoRegistradoException("No hay hotel cargado");
        }
        this.hotel.agregarDeluxe( descripcion, personasPermitidas, servicioEspecialDeluxe, disponible, motivoNoDisponible);
    }
    public boolean eliminarHabitacion(int idHabitacion){
        boolean encontrado=false;
        try{
            encontrado=this.hotel.eliminarHabitacion(idHabitacion);
        }catch (NoRegistradoException e){
            System.out.println(e.getMessage());
        }
        return encontrado;
    }

    public String mostrarRecepcionista() throws NoRegistradoException {
        if (this.recepcionista==null){
            throw new NoRegistradoException("No hay recepcionista registrado");
        }
        return this.recepcionista.mostrar();
    }

    public JSONObject toJSON() {
        JSONObject obj = super.toJson();
        return obj;
    }

}
