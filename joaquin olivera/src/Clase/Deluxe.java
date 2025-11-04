package Clase;

import Enums.ServicioEsepcialDeluxe;

public class Deluxe extends Habitacion{
    private ServicioEsepcialDeluxe servicioEsepcialDeluxe;

    public Deluxe(int id, int precio, String descripcion, boolean estado, String servicios, int personasPermitidas, ServicioEsepcialDeluxe servicioEsepcialDeluxe) {
        super(id, precio, descripcion, estado, servicios, personasPermitidas);
        this.servicioEsepcialDeluxe = servicioEsepcialDeluxe;
    }

    public ServicioEsepcialDeluxe getServicioEsepcialDeluxe() {
        return servicioEsepcialDeluxe;
    }

    @Override
    public String toString() {
        return super.toString()+"Deluxe{" +
                "servicioEsepcialDeluxe=" + servicioEsepcialDeluxe +
                '}';
    }
}
