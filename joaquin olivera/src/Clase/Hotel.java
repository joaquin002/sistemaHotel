package Clase;

import Enums.MotivoNoDisponible;
import Enums.ServicioEspecialDeluxe;
import Enums.ServicioEspecialSuite;
import Excepcion.NoRegistradoException;
import Interfaces.Identificable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Hotel implements Identificable{
    private int id;
    private String nombre;
    private String direccion;
    private double recaudacion;
    private Registro<Habitacion> habitaciones;

    public Hotel(int id, String nombre, String direccion) {
        this.id = id;
        this.recaudacion=0;
        this.nombre = nombre;
        this.direccion = direccion;
        this.habitaciones = new Registro<>();
    }
    public Hotel (JSONObject obj) throws JSONException{
        this.id = obj.getInt("id");
        this.nombre = obj.getString("nombre");
        this.direccion = obj.getString("direccion");
        this.recaudacion = obj.getDouble("recaudacion");
        this.habitaciones = new Registro<>();

        JSONArray habitaciones=obj.getJSONArray("habitaciones");

        for (int i = 0; i < habitaciones.length(); i++) {
            JSONObject habitacion=habitaciones.getJSONObject(i);
            this.habitaciones.agregar(new Habitacion(habitacion));
        }
    }

    public Registro<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public double getRecaudacion() {
        return recaudacion;
    }

    @Override
    public int getIdBuscado() {
        return this.id;
    }

    @Override
    public String toString() {
        return "--------------HOTEL----------------" +'\n' +
                "id= " + id + '\n'+
                "nombre= " + nombre + '\n' +
                "direccion= " + direccion + '\n' +
                "recaudacion= " + recaudacion + '\n'+
                "habitaciones registradas... " + mostrarHabitaciones()+ '\n';
    }

    public void agregarHabitacionEstandar( String descripcion, int personasPermitidas, boolean estado, MotivoNoDisponible motivoNoDisponible) {
        Habitacion estandar=new Habitacion( descripcion, personasPermitidas, estado, motivoNoDisponible);
        this.habitaciones.agregar(estandar);
    }
    public void agregarSuite( String descripcion, int personasPermitidas, ServicioEspecialSuite especialSuite, boolean disponible, MotivoNoDisponible motivoNoDisponible) {
        Suite s1=new Suite( descripcion, personasPermitidas, especialSuite, disponible, motivoNoDisponible);
        this.habitaciones.agregar(s1);
    }
    public void agregarDeluxe( String descripcion, int personasPermitidas, ServicioEspecialDeluxe servicioEspecialDeluxe, boolean disponible, MotivoNoDisponible motivoNoDisponible) {
        Deluxe d1=new Deluxe( descripcion, personasPermitidas, servicioEspecialDeluxe, disponible, motivoNoDisponible);
        this.habitaciones.agregar(d1);
    }

    public boolean eliminarHabitacion(int idBuscado) throws NoRegistradoException {
        if (!this.habitaciones.buscarPorId(idBuscado)) {
            throw new NoRegistradoException("Habitacion no encontrada");
        }
       return this.habitaciones.eliminar(habitaciones.buscar(idBuscado));
    }

    public String mostrarHabitacion(int idBuscado)throws NoRegistradoException {
        if (!this.habitaciones.buscarPorId(idBuscado)) {
            throw new NoRegistradoException("Habitacion no encontrada");
        }
        return this.habitaciones.mostrarPorId(idBuscado);
    }


    public Habitacion buscarHabitacion(int idHabitacion) {
        for (Habitacion h : this.habitaciones.getLista()) {
            if (h.getId() == idHabitacion) {
                return h;
            }
        }
        return null;
    }


    public void sumarRecaudacion(double recaudacion) {
        if (recaudacion > 0) {
            this.recaudacion += recaudacion;
        }
    }


    public String mostrarHabitaciones(){ // ver
        String rta="";
        for (Habitacion h : this.habitaciones.getLista()) {
            rta+=h.toString()+'\n';
        }
        return rta;
    }
    public JSONObject toJSON(){
        JSONObject obj=new JSONObject();
        try{
            obj.put("id", this.id);
            obj.put("nombre", this.nombre);
            obj.put("direccion", this.direccion);
            obj.put("recaudacion", this.recaudacion);
            JSONArray habitacionJson=new JSONArray();
            for (Habitacion h:this.habitaciones.getLista()) {
                habitacionJson.put(h.toJson());
            }
            obj.put("habitaciones",habitacionJson);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return obj;
    }

}
