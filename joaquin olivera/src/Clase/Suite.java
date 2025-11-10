package Clase;

import Enums.ServicioEspecialSuite;

import java.util.ArrayList;
import java.util.PrimitiveIterator;

public class Suite extends Habitacion{
    private ServicioEspecialSuite especialSuite;

    public Suite(int precio, String descripcion,String servicios, int personasPermitidas, ServicioEspecialSuite especialSuite, boolean disponible) {
        super( precio, descripcion,servicios, personasPermitidas, disponible);
        this.especialSuite = especialSuite;
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
}
