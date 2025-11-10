package Clase;


import Interfaces.Identificable;

public class Habitacion implements Identificable {
    private int id;
    private static int contador=1;
    private double precio;
    private String descripcion;
    private String servicios;
    private int cantPersonas;
    private boolean disponible; //false ocupada true disponible
    private String motivoNoDisponible;

    public Habitacion( double precio, String descripcion,String servicios, int cantPersonas,  boolean disponible, String motivoNoDisponible) {
        this.id = contador++;
        this.precio = precio;
        this.descripcion = descripcion;
        this.servicios = servicios;
        this.cantPersonas =  cantPersonas;
        this.disponible = disponible;
        this.motivoNoDisponible=motivoNoDisponible;
    }

    public String getMotivoNoDisponible() {
        return motivoNoDisponible;
    }

    public void setMotivoNoDisponible(String motivoNoDisponible) {
        this.motivoNoDisponible = motivoNoDisponible;
    }

    public int getId() {
        return id;
    }

    public double getPrecio() {
        return precio;
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
                ", personasPermitidas=" + cantPersonas +
                ", disponible=" + disponible +
                ", motivoNoDisponible='" + motivoNoDisponible + '\'' +
                '}';
    }
}
