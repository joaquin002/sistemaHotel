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
    public Historial(JSONObject obj) throws JSONException {
        this.dniCliente = obj.getInt("dniCliente");
        this.fechaInicio = obj.getString("fechaInicio");
        this.fechaSalida = obj.getString("fechaSalida");
    }


    @Override
    public String toString() {
        return "Historial{" +
                "dniCliente=" + dniCliente +
                ", fechaInicio='" + fechaInicio + '\'' +
                ", fechaSalida='" + fechaSalida + '\'' +
                '}';
    }

    public JSONObject toJson(){
        JSONObject json = new JSONObject();
        try {
            json.put("dniCliente", this.dniCliente);
            json.put("fechaInicio", this.fechaInicio);
            json.put("fechaSalida", this.fechaSalida);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return json;
    }
}
