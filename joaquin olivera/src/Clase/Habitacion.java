package Clase;


public class Habitacion {
    private int id;
    private int precio;
    private String descripcion;
    private boolean estado;
    private String servicios;
    private int personasPermitidas;

    public Habitacion(int id, int precio, String descripcion, boolean estado, String servicios, int personasPermitidas) {
        this.id = id;
        this.precio = precio;
        this.descripcion = descripcion;
        this.estado = estado;
        this.servicios = servicios;
        this.personasPermitidas =  personasPermitidas;
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
                ", servicios='" + servicios + '\'' +
                ", personasPermitidas=" + personasPermitidas +
                '}';
    }

    /// metodos futuros
}
