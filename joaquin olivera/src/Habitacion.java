public class Habitacion {
    private int id; //numero de habitación
    private static int contador = 1;
    private int precio;
    private boolean descuento;
    private String descripcion;
    private Eestado estado;
    private EtipoHabitacion tipo;
    private EcantPersonas cantPersonas;
    private String servicios; //fijarse si va a hacer string o enum

    public Habitacion(int precio, String descripcion, EtipoHabitacion tipo, EcantPersonas cantPersonas, String servicios,Eestado estado) {
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

    public Eestado getEstado() {
        return estado;
    }

    public EtipoHabitacion getTipo() {
        return tipo;
    }

    public EcantPersonas getCantPersonas() {
        return cantPersonas;
    }

    public String getServicios() {
        return servicios;
    }

    @Override
    public String toString() { //hacer el toString personalizado
        return "Habitacion{" +
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
