package Clase;

import Enums.ServicioEsepcialDeluxe;
import Enums.ServicioEspecialSuite;
import Excepcion.NoRegistradoEx;
import Interfaces.Identificable;
import Interfaces.IhotelOperable;

import java.util.ArrayList;

public class Hotel implements Identificable, IhotelOperable {
    private int id;
    private String nombre;
    private String direccion;
    private int recaudacion;
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

    public int getRecaudacion() {
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
                ", habitaciones=" + mostrarTodasLasHabitaciones()+
                '}';
    }

    public void agregarHabitacionEstandar(int id, int precio, String descripcion,String servicios, int personasPermitidas, boolean estado) {
        Habitacion estandar=new Habitacion(id, precio, descripcion, servicios, personasPermitidas, estado);
        this.habitaciones.agregar(estandar);
    }
    public void agregarSuite(int id, int precio, String descripcion,String servicios, int personasPermitidas, ServicioEspecialSuite especialSuite, boolean disponible){
        Suite s1=new Suite(id, precio, descripcion, servicios, personasPermitidas, especialSuite, disponible);
        this.habitaciones.agregar(s1);
    }
    public void agregarDeluxe(int id, int precio, String descripcion,String servicios, int personasPermitidas, ServicioEsepcialDeluxe servicioEsepcialDeluxe, boolean disponible){
        Deluxe d1=new Deluxe(id, precio, descripcion, servicios, personasPermitidas,  servicioEsepcialDeluxe, disponible);
        this.habitaciones.agregar(d1);
    }

    public boolean elimarHabitacion(int idBuscado) throws NoRegistradoEx {
        if (!this.habitaciones.buscarPorId(idBuscado)) {
            throw new NoRegistradoEx("Habitacion no encontrada");
        }
       return this.habitaciones.eliminar(habitaciones.buscar(idBuscado));
    }

    public String mostrarHabitacion(int idBuscado)throws NoRegistradoEx {
        if (!this.habitaciones.buscarPorId(idBuscado)) {
            throw new NoRegistradoEx("Habitacion no encontrada");
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


    public void sumarRecaudacion(int recaudacion) {
        if (recaudacion > 0) {
            this.recaudacion += recaudacion;
        }
    }


    public String mostrarTodasLasHabitaciones(){
        String rta="";
        for (Habitacion h : this.habitaciones.getLista()) {
            rta+=h.toString()+'\n';
        }
        return rta;
    }


}
