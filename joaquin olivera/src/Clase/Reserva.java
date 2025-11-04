package Clase;

public class Reserva {
    private int idReserva;
    private int dniCliente;
    private int idRecepcionista;
    private String fecha; // lo hacemos con el formato

    public Reserva(int idReserva, int dniCliente, int idRecepcionista, String fecha) {
        this.idReserva = idReserva;
        this.dniCliente = dniCliente;
        this.idRecepcionista = idRecepcionista;
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "idReserva=" + idReserva +
                ", dniCliente=" + dniCliente +
                ", idRecepcionista=" + idRecepcionista +
                ", fecha='" + fecha + '\'' +
                '}';
    }


}
