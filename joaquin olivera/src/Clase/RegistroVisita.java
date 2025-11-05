package Clase;

public class RegistroVisita {
    private int id;
    private static int cont = 1;
    private int idCliente;
    private int cantidad; // de visitas
    private int idHotel; //del hotel al que fue.
    private String fechaEstadia; //fecha de cuando estuvo

    public RegistroVisita(int idCliente, int cantidad, int idHotel,  String fechaEstadia) {
        this.id = cont++;
        this.idCliente = idCliente;
        this.cantidad = cantidad;
        this.idHotel = idHotel;
        this.fechaEstadia = fechaEstadia;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public int getIdHotel() {
        return idHotel;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getFechaEstadia() {
        return fechaEstadia;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setFechaEstadia(String fechaEstadia) {
        this.fechaEstadia = fechaEstadia;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "RegistroVisita{" +
                "id=" + id +
                ", idCliente=" + idCliente +
                ", cantidad=" + cantidad +
                ", idHotel=" + idHotel +
                ", fechaEstadia='" + fechaEstadia + '\'' +
                '}';
    }
}
