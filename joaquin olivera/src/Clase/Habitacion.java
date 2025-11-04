package Clase;


import Interfaces.Identificable;

public class Habitacion implements Identificable {
    private int id;
    private int precio;
    private String descripcion;
    private String servicios;
    private int personasPermitidas;

    public Habitacion(int id, int precio, String descripcion,String servicios, int personasPermitidas) {
        this.id = id;
        this.precio = precio;
        this.descripcion = descripcion;
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

    public String getServicios() {
        return servicios;
    }

    public int getPersonasPermitidas() {
        return personasPermitidas;
    }

    @Override
    public int getIdBuscado() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Habitacion{" +
                "id=" + id +
                ", precio=" + precio +
                ", descripcion='" + descripcion + '\'' +
                ", servicios='" + servicios + '\'' +
                ", personasPermitidas=" + personasPermitidas +
                '}';
    }

    /// metodos futuros
}
