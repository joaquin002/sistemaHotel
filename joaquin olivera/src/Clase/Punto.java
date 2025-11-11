package Clase;

import org.json.JSONException;
import org.json.JSONObject;

public class Punto {
    private int id;
    private int dniCliente;
    private int cantidad;

    public Punto(int id, int dniCliente, int cantidad) {
        this.id = id;
        this.dniCliente = dniCliente;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public int getDniCliente() {
        return dniCliente;
    }

    public int getCantidad() {
        return cantidad;
    }

    @Override
    public String toString() {
        return "Punto{" +
                "id=" + id +
                ", dniCliente=" + dniCliente +
                ", cantidad=" + cantidad +
                '}';
    }
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        try {
            json.put("id", id);
            json.put("dniCliente", dniCliente);
            json.put("cantidad", cantidad);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return json;
    }
}
