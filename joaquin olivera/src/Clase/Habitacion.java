package Clase;
import Enums.CantidadPersonas;
import Enums.TipoHabitacion;


public class Habitacion {
    private int id;
    private int precio;
    private String descripcion;
    private boolean estado;
    private TipoHabitacion tipo;
    private String servicios;
    private int personasPermitidas;

    public Habitacion(int id, int precio, String descripcion, boolean estado, TipoHabitacion tipo, String servicios) {
        this.id = id;
        this.precio = precio;
        this.descripcion = descripcion;
        this.estado = estado;
        this.tipo = tipo;
        this.servicios = servicios;
        this.personasPermitidas = 4;
    }

    public int getId() {
        return id;
    }

    public int getPrecio() {
        return precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean getEstado() {
        return estado;
    }

    public TipoHabitacion getTipo() {
        return tipo;
    }

    public String getServicios() {
        return servicios;
    }

    public int getPersonasPermitidas() {
        return personasPermitidas;
    }

    @Override
    public String toString() {
        return "Habitacion{" +
                "id=" + id +
                ", precio=" + precio +
                ", descripcion='" + descripcion + '\'' +
                ", estado=" + estado +
                ", tipo=" + tipo +
                ", servicios='" + servicios + '\'' +
                ", personasPermitidas=" + personasPermitidas +
                '}';
    }
    /// metodos futuros
}
