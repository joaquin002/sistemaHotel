package Clase;

public class RegistroVisita {
    private int id;
private int idCliente;
private int cantidad; // de visitas
private int idHotel; //del hotel al que fue.

    public RegistroVisita(int id, int idCliente, int cantidad, int idHotel) {
        this.id = id;
        this.idCliente = idCliente;
        this.cantidad = cantidad;
        this.idHotel = idHotel;
    }
}
