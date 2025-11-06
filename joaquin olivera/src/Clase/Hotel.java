package Clase;

import Enums.ServicioEsepcialDeluxe;
import Enums.ServicioEspecialSuite;
import Interfaces.Identificable;
import Interfaces.IhotelOperable;

import java.util.ArrayList;

public class Hotel implements Identificable, IhotelOperable {
    private int id;
    private String nombre;
    private String direccion;
    private int recaudacion = 0;
    private Registro<Habitacion> habitaciones;

    public Hotel(int id, String nombre, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.habitaciones = new Registro<>();
    }

    @Override
    public int getIdHotel() {
        return this.id;
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
                ", habitaciones=" + mostrarTodasLasHabitaciones()+
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
    public Habitacion buscarHabitacion(int idHabitacion) {
        for (Habitacion h : this.habitaciones.getLista()) {
            if (h.getId() == idHabitacion) {
                return h;
            }
        }
        return null;
    }
    public void sumarRecaudacion(int recaudacion) {
        if (recaudacion > 0) {
            this.recaudacion += recaudacion;
        }
    }

    public String mostrarTodasLasHabitaciones()
    {
        String rta="";
        for (Habitacion h : this.habitaciones.getLista()) {
            rta+=h.toString()+'\n';
        }
        return rta;
    }


}
