package Clase;

import Enums.MotivoNoDisponible;
import Enums.ServicioEspecialSuite;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.font.TextHitInfo;
import java.util.ArrayList;
import java.util.PrimitiveIterator;

public class Suite extends Habitacion{
    private ServicioEspecialSuite especialSuite;
    public static final double precioSuite=5000;

    public Suite(String descripcion, int personasPermitidas, ServicioEspecialSuite especialSuite, boolean disponible, MotivoNoDisponible motivoNoDisponible) {
        super( descripcion, personasPermitidas, disponible, motivoNoDisponible);
        super.setPrecio(precioSuite);
        this.especialSuite = especialSuite;
    }
    public Suite(JSONObject obj) throws JSONException {
        super(obj);
        this.especialSuite=obj.getEnum(ServicioEspecialSuite.class, "especialSuite");
    }

    @Override
    public String toString() {
        return super.toString()+"--- serivicios Suite---"
                + especialSuite;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = super.toJson();
         try{
             json.put("especialSuite", this.especialSuite);
         }catch (JSONException e){
             e.printStackTrace();
         }
         return json;
    }
}
