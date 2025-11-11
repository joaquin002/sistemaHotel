package Clase;

import org.json.JSONException;
import org.json.JSONObject;

public class Historial {
    private int dniCliente;
    private String fechaInicio;// con formato
    private String fechaSalida;

    public Historial(int dniCliente, String fechaInicio, String fechaSalida) {
        this.dniCliente = dniCliente;
        this.fechaInicio = fechaInicio;
        this.fechaSalida = fechaSalida;
    }

    public int getDniCliente() {
        return dniCliente;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    @Override
    public String toString() {
        return "Historial{" +
                "dniCliente=" + dniCliente +
                ", fechaInicio='" + fechaInicio + '\'' +
                ", fechaSalida='" + fechaSalida + '\'' +
                '}';
    }

    public JSONObject toJSON(){
        JSONObject json = new JSONObject();
        try {
            json.put("dniCliente", dniCliente);
            json.put("fechaInicio", fechaInicio);
            json.put("fechaSalida", fechaSalida);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return json;
    }
}
