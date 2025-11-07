package Clase;


import Interfaces.Identificable;

public class Habitacion implements Identificable {
    private int id;
    private int precio;
    private String descripcion;
    private String servicios;
    private int personasPermitidas;
    private boolean disponible;

    public Habitacion(int id, int precio, String descripcion,String servicios, int personasPermitidas,  boolean disponible) {
        this.id = id;
        this.precio = precio;
        this.descripcion = descripcion;
        this.servicios = servicios;
        this.personasPermitidas =  personasPermitidas;
        this.disponible = disponible;
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

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
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
}
