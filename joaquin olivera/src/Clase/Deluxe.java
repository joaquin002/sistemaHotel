package Clase;

import Enums.ServicioEsepcialDeluxe;

public class Deluxe extends Habitacion{
    private ServicioEsepcialDeluxe servicioEsepcialDeluxe;

    public Deluxe( int precio, String descripcion,String servicios, int personasPermitidas, ServicioEsepcialDeluxe servicioEsepcialDeluxe, boolean disponible) {
        super( precio, descripcion,servicios, personasPermitidas, disponible);
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
