package Interfaces;

import Clase.Habitacion;

public interface IhotelOperable {
    Habitacion buscarHabitacion(int idHabitacion);
    void sumarRecaudacion(int montoAsumar);
    int getIdHotel();
}
