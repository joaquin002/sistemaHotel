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

    public int getId() {
        return id;
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
        return "Hotel{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", recaudacion=" + recaudacion +
                ", habitaciones=" + mostrarHabitaciones()+
                '}';
    }

    public void agregarHabitacionEstandar(double precio, String descripcion,String servicios, int personasPermitidas, boolean estado, MotivoNoDisponible motivoNoDisponible) {
        Habitacion estandar=new Habitacion( precio, descripcion, servicios, personasPermitidas, estado, motivoNoDisponible);
        this.habitaciones.agregar(estandar);
    }
    public void agregarSuite(double precio, String descripcion,String servicios, int personasPermitidas, ServicioEspecialSuite especialSuite, boolean disponible, MotivoNoDisponible motivoNoDisponible) {
        Suite s1=new Suite(precio, descripcion, servicios, personasPermitidas, especialSuite, disponible, motivoNoDisponible);
        this.habitaciones.agregar(s1);
    }
    public void agregarDeluxe(double precio, String descripcion, String servicios, int personasPermitidas, ServicioEspecialDeluxe servicioEspecialDeluxe, boolean disponible, MotivoNoDisponible motivoNoDisponible) {
        Deluxe d1=new Deluxe(precio, descripcion, servicios, personasPermitidas, servicioEspecialDeluxe, disponible, motivoNoDisponible);
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

    // REVISAR
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
            obj.put("id",id);
            obj.put("nombre",nombre);
            obj.put("direccion",direccion);
            obj.put("recaudacion",recaudacion);
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
