package Clase;


import Enums.MotivoNoDisponible;
import Interfaces.Identificable;
import org.json.JSONException;
import org.json.JSONObject;

public class Habitacion implements Identificable {
    private int id;
    private static int contador=1;
    private double precio;
    private String descripcion;
    private String servicios;
    private int cantPersonas;
    private boolean disponible; //false ocupada true disponible
    private MotivoNoDisponible motivoNoDisponible;

    public Habitacion( double precio, String descripcion,String servicios, int cantPersonas,  boolean disponible, MotivoNoDisponible motivoNoDisponible) {
        this.id = contador++;
        this.precio = precio;
        this.descripcion = descripcion;
        this.servicios = servicios;
        this.cantPersonas =  cantPersonas;
        this.disponible = disponible;
        this.motivoNoDisponible=motivoNoDisponible;
    }

    public Habitacion(JSONObject obj) throws JSONException {
        this.id = obj.getInt("id");
        this.precio = obj.getDouble("precio");
        this.descripcion = obj.getString("descripcion");
        this.servicios = obj.getString("servicios");
        this.cantPersonas = obj.getInt("cantPersonas");
        this.disponible = obj.getBoolean("disponible");
        this.motivoNoDisponible = obj.getEnum(MotivoNoDisponible.class,"motivoNoDisponible");
    }

    public int getId() {
        return id;
    }

    public double getPrecio() {
        return precio;
    }


    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public int getIdBuscado() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Habitacion{" +
                "id=" + id +
                ", precio=" + precio +
                ", descripcion='" + descripcion + '\'' +
                ", servicios='" + servicios + '\'' +
                ", personasPermitidas=" + cantPersonas +
                ", disponible=" + disponible +
                ", motivoNoDisponible='" + motivoNoDisponible + '\'' +
                '}';
    }
    public JSONObject toJson(){
        JSONObject json = new JSONObject();
        try{
            json.put("id", this.id);
            json.put("precio", this.precio);
            json.put("descripcion", this.descripcion);
            json.put("servicios", this.servicios);
            json.put("personasPermitidas", this.cantPersonas);
            json.put("disponible", this.disponible);
            json.put("motivoNoDisponible", this.motivoNoDisponible);
        }catch(JSONException e){
            e.printStackTrace();
        }
        return json;
    }
}
