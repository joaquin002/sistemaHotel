package Clase;

import Enums.ServicioEspecialDeluxe;
import org.json.JSONException;
import org.json.JSONObject;

public class Deluxe extends Habitacion{
    private ServicioEspecialDeluxe servicioEspecialDeluxe;

    public Deluxe(double precio, String descripcion, String servicios, int personasPermitidas, ServicioEspecialDeluxe servicioEspecialDeluxe, boolean disponible, String motivoNoDisponible) {
        super( precio, descripcion,servicios, personasPermitidas, disponible, motivoNoDisponible);
        this.servicioEspecialDeluxe = servicioEspecialDeluxe;
    }

    public ServicioEspecialDeluxe getServicioEsepcialDeluxe() {
        return servicioEspecialDeluxe;
    }

    @Override
    public String toString() {
        return super.toString()+"Deluxe{" +
                "servicioEsepcialDeluxe=" + servicioEspecialDeluxe +
                '}';
    }

    @Override
    public JSONObject toJson(){
        JSONObject json = new JSONObject();
        try{
            json =  super.toJson();
            json.put("servicioEspcialDeluxe",servicioEspecialDeluxe);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return json;
    }
}
