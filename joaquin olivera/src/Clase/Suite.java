package Clase;

import Enums.ServicioEspecialSuite;

import java.util.ArrayList;
import java.util.PrimitiveIterator;

public class Suite extends Habitacion{
    private ServicioEspecialSuite especialSuite;

    public Suite(int id, int precio, String descripcion, boolean estado, String servicios, int personasPermitidas, ServicioEspecialSuite especialSuite) {
        super(id, precio, descripcion, estado, servicios, personasPermitidas);
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
