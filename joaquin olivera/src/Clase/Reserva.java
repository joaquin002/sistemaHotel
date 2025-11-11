package Clase;

import Interfaces.Identificable;
import org.json.JSONException;
import org.json.JSONObject;

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

    //constructor para cliente
    public Reserva(int dniCliente, String fecha, int idHabitacion) {
        this.idReserva = cont++;
        this.dniCliente = dniCliente;
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
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        try{
            json.put("idReserva", idReserva);
            json.put("dniCliente", dniCliente);
            json.put("idRecepcionista", idRecepcionista);
            json.put("fecha", fecha);
            json.put("idHabitacion", idHabitacion);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return json;
    }
}
