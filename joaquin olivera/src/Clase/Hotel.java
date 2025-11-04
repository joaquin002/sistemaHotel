package Clase;

import java.util.ArrayList;

public class Hotel {
    private int id;
    private String nombre;
    private String direccion;
    private int recaudacion;
    private ArrayList<Habitacion> habitaciones;

    public Hotel(int id, String nombre, String direccion, int recaudacion) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.recaudacion = recaudacion;
        this.habitaciones = new ArrayList<>();
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

    public ArrayList<Habitacion> getHabitaciones() {
        return habitaciones;
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

}
