package Clase;
import Enums.CantidadPersonas;
import Enums.TipoHabitacion;


public class Habitacion {
    private int id; //numero de habitación
    private static int contador = 1;
    private int precio;
    private boolean descuento;
    private String descripcion;
    private boolean estado;
    private TipoHabitacion tipo;
    private CantidadPersonas cantPersonas;
    private String servicios; //fijarse si va a hacer string o enum

    public Habitacion(int precio, String descripcion, TipoHabitacion tipo, CantidadPersonas cantPersonas, String servicios, boolean estado) {
        this.precio = precio;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.cantPersonas = cantPersonas;
        this.servicios = servicios;
        this.id = contador++;
        this.descuento=false;
        this.estado=estado;
    } //este constructor puede cambiar

    //getters, aun no hay setters
    public int getId() {
        return id;
    }

    public int getPrecio() {
        return precio;
    }

    public boolean isDescuento() {
        return descuento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean isEstado() {
        return estado;
    }

    public TipoHabitacion getTipo() {
        return tipo;
    }

    public CantidadPersonas getCantPersonas() {
        return cantPersonas;
    }

    public String getServicios() {
        return servicios;
    }

    @Override
    public String toString() { //hacer el toString personalizado
        return "Clase.Habitacion{" +
                "id=" + id +
                ", precio=" + precio +
                ", descuento=" + descuento +
                ", descripcion='" + descripcion + '\'' +
                ", estado=" + estado +
                ", tipo=" + tipo +
                ", cantPersonas=" + cantPersonas +
                ", servicios='" + servicios + '\'' +
                '}';
    }
    /// metodos futuros
}
