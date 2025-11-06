package Clase;

import Interfaces.Identificable;

public class Reserva implements Identificable {
    private int idReserva;
    private static int cont=1;
    private int dniCliente;
    private int idRecepcionista;
    private String fecha; // lo hacemos con el formato
    private int idHabitacion;

    public Reserva(int dniCliente, int idRecepcionista, String fecha, int idHabitacion) {
        this.idReserva = cont++;
        this.dniCliente = dniCliente;
        this.idRecepcionista = idRecepcionista;
        this.fecha = fecha;
        this.idHabitacion = idHabitacion;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public int getDniCliente() {
        return dniCliente;
    }

    public int getIdRecepcionista() {
        return idRecepcionista;
    }

    public String getFecha() {
        return fecha;
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
