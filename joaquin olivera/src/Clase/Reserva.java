package Clase;

import Interfaces.Identificable;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;

public class Reserva implements Identificable {
    private int idReserva;
    private static int cont=1;
    private int dniCliente;
    private int idRecepcionista;
    private LocalDate fechaInicio;
    private LocalDate fechaFinalizacion;
    private int idHabitacion;

    public Reserva(int dniCliente, int idRecepcionista,LocalDate fechaInicio,LocalDate fechaFinalizacion, int idHabitacion) {
        this.idReserva = cont++;
        this.dniCliente = dniCliente;
        this.idRecepcionista = idRecepcionista;
        this.fechaInicio = fechaInicio;
        this.fechaFinalizacion = fechaFinalizacion;
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

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFinalizacion() {
        return fechaFinalizacion;
    }
    
    @Override
    public String toString() {
        return "Reserva{" +
                "idReserva=" + idReserva +
                ", dniCliente=" + dniCliente +
                ", idRecepcionista=" + idRecepcionista +
                ", fechaInicio=" + fechaInicio +
                ", fechaFinalizacion=" + fechaFinalizacion +
                ", idHabitacion=" + idHabitacion +
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
            json.put("fecha", this.fechaInicio);
            json.put("fechaFinalizacion", this.fechaFinalizacion);
            json.put("idHabitacion", idHabitacion);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return json;
    }
}
