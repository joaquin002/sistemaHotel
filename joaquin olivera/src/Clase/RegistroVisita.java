package Clase;

public class RegistroVisita {
    private int id;
    private static int cont = 1;
    private int dniCliente;
    private int cantidad; // de visitas
    private int idHotel; //del hotel al que fue.
    private String fechaEstadia; //fecha de cuando estuvo

    public RegistroVisita(int dniCliente, int cantidad, int idHotel,  String fechaEstadia) {
        this.id = cont++;
        this.dniCliente = dniCliente;
        this.cantidad = cantidad;
        this.idHotel = idHotel;
        this.fechaEstadia = fechaEstadia;
    }

    public int getDniCliente() {
        return dniCliente;
    }

    public int getIdHotel() {
        return idHotel;
    }

    public int getCantidad() {
        return cantidad;
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
                ", dniCliente=" + dniCliente +
                ", cantidad=" + cantidad +
                ", idHotel=" + idHotel +
                ", fechaEstadia='" + fechaEstadia + '\'' +
                '}';
    }
}
