package Clase;

import Interfaces.Identificable;

public class Reserva implements Identificable {
    private int idReserva;
    private static int cont=1;
    private int dniCliente;
    private int idRecepcionista;
    private String fecha; // lo hacemos con el formato

    public Reserva(int dniCliente, int idRecepcionista, String fecha) {
        this.idReserva = cont++;
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


    @Override
    public int getIdBuscado() {
        return this.idReserva;
    }
}
