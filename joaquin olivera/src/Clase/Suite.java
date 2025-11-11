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

    public Suite(double precio, String descripcion,String servicios, int personasPermitidas, ServicioEspecialSuite especialSuite, boolean disponible, MotivoNoDisponible motivoNoDisponible) {
        super(precio, descripcion,servicios, personasPermitidas, disponible, motivoNoDisponible);
        this.especialSuite = especialSuite;
    }
    public Suite(JSONObject obj) throws JSONException {
        super(obj);
        this.especialSuite=obj.getEnum(ServicioEspecialSuite.class, "especialSuite");
    }

    public ServicioEspecialSuite getEspecialSuite() {
        return especialSuite;
    }

    @Override
    public String toString() {
        return super.toString()+"Suite{" +
                "especialSuite=" + especialSuite +
                '}';
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = super.toJson();
         try{
             json = super.toJson();
             json.put("especialSuite",especialSuite);
         }catch (JSONException e){
             e.printStackTrace();
         }
         return json;
    }
}
