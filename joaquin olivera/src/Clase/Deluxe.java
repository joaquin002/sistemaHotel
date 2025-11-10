package Clase;

import Enums.ServicioEspecialDeluxe;

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
}
