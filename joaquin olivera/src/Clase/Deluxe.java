package Clase;

import Enums.MotivoNoDisponible;
import Enums.ServicioEspecialDeluxe;
import org.json.JSONException;
import org.json.JSONObject;

public class Deluxe extends Habitacion{
    private ServicioEspecialDeluxe servicioEspecialDeluxe;
    public static final double precioDeluxe=3000;

    public Deluxe( String descripcion, int personasPermitidas, ServicioEspecialDeluxe servicioEspecialDeluxe, boolean disponible, MotivoNoDisponible motivoNoDisponible) {
        super( descripcion, personasPermitidas, disponible, motivoNoDisponible);
        super.setPrecio(precioDeluxe);
        this.servicioEspecialDeluxe = servicioEspecialDeluxe;
    }
    public Deluxe(JSONObject obj) throws JSONException {
        super(obj);
        this.servicioEspecialDeluxe=obj.getEnum(ServicioEspecialDeluxe.class,"servicioEspecialDeluxe");
    }

    @Override
    public String toString() {
        return super.toString()+"---servicios Deluxe---" + servicioEspecialDeluxe;
    }

    @Override
    public JSONObject toJson(){
        JSONObject json = super.toJson();
        try{
            json.put("servicioEspcialDeluxe", this.servicioEspecialDeluxe);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return json;
    }
}
