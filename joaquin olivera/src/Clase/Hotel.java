package Clase;

import Enums.ServicioEsepcialDeluxe;
import Enums.ServicioEspecialSuite;
import Interfaces.Identificable;

import java.util.ArrayList;

public class Hotel implements Identificable{
    private int id;
    private String nombre;
    private String direccion;
    private int recaudacion;
    private Registro<Habitacion> habitaciones;

    public Hotel(int id, String nombre, String direccion, int recaudacion) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.recaudacion = recaudacion;
        this.habitaciones = new Registro<>();
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getRecaudacion() {
        return recaudacion;
    }

    public Registro<Habitacion> getHabitaciones() {
        return habitaciones;
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
                ", habitaciones=" + habitaciones +
                '}';
    }

    public void agregarHabitacionEstandar(int id, int precio, String descripcion,String servicios, int personasPermitidas, boolean estado) {
        this.habitaciones.agregar(new Habitacion(id, precio, descripcion, servicios, personasPermitidas, estado));
    }
    public void agregarSuite(int id, int precio, String descripcion,String servicios, int personasPermitidas, ServicioEspecialSuite especialSuite, boolean disponible){
        this.habitaciones.agregar(new Suite(id, precio, descripcion,servicios, personasPermitidas, especialSuite,disponible));
    }
    public void agregarDeluxe(int id, int precio, String descripcion,String servicios, int personasPermitidas, ServicioEsepcialDeluxe servicioEsepcialDeluxe, boolean disponible){
        this.habitaciones.agregar(new Deluxe(id, precio, descripcion,servicios, personasPermitidas, servicioEsepcialDeluxe, disponible));
    }
    public void elimarHabitacion(int idBuscado){
        this.habitaciones.eliminar(habitaciones.buscar(idBuscado));
    }
    public String mostrarHabitacion(int idBuscado){
        return this.habitaciones.mostrarPorId(idBuscado);
    }
}
