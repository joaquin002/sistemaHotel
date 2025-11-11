package Clase;

import org.json.JSONException;
import org.json.JSONObject;

public class Historial {
    private int dniCliente;
    private String fechaEstadia; // con formato

    public Historial(int dniCliente, String fechaEstadia) {
        this.dniCliente = dniCliente;
        this.fechaEstadia = fechaEstadia;
    }

    public int getDniCliente() {
        return dniCliente;
    }

    public String getFechaEstadia() {
        return fechaEstadia;
    }

    @Override
    public String toString() {
        return "Historial{" +
                "dniCliente=" + dniCliente +
                ", fechaEstadia='" + fechaEstadia + '\'' +
                '}';
    }
    public JSONObject toJSON(){
        JSONObject json = new JSONObject();
        try {
            json.put("dniCliente", dniCliente);
            json.put("fechaEstadia", fechaEstadia);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return json;
    }
}
