package Clase;

import Excepcion.NoRegistradoEx;
import Interfaces.Identificable;
import Interfaces.IhotelOperable;

import java.util.ArrayList;

public class Recepcionista extends Usuario implements Identificable {
    private int id;
    private Registro<Cliente> clientes;
    private Registro<Reserva> reservas;
    private ArrayList<Punto> puntos;
    private ArrayList<RegistroVisita> registroVisitas;
    private IhotelOperable hotel; //una variable de tipo interface que solo te deja utilizar los metodos elegidos

    //para parte de usuario
    public Recepcionista(String nombreUsuario, String contrasenia) {
        super(nombreUsuario, contrasenia,"Recepcionista");
    }

    public Recepcionista(int id,Hotel hotel) {
        this.id = id;
        this.hotel = hotel;
        this.clientes = new Registro<>();
        this.reservas = new Registro<>();
        this.puntos = new ArrayList<>();
        this.registroVisitas = new ArrayList<>();
    }

    @Override
    public int getIdBuscado() {
        return this.id;
    }

    public void checkIn(int dniCliente, int idHabitacion, String fechaEstadia) throws NoRegistradoEx {
        //verifica cliente
        if (!clientes.buscarPorId(dniCliente)) {
            throw new NoRegistradoEx("el cliente con dni " + dniCliente + " no esta registrado.");
        }

        //verifica habitación
        Habitacion habitacionEncontrada = this.hotel.buscarHabitacion(idHabitacion);

        if (habitacionEncontrada == null) {
            throw new NoRegistradoEx("a habitacion con id " + idHabitacion + " no existe.");
        }
        //verifica disponibilidad de la habitacion
        if (!habitacionEncontrada.isDisponible()) {
            throw new NoRegistradoEx("la habitacion con id " + idHabitacion + " no esta disponible.");
        }

        //crea reserva
        Reserva nuevaReserva = new Reserva(dniCliente, this.id, fechaEstadia);
        reservas.agregar(nuevaReserva);

        //marca la habitacion como ocupada
        habitacionEncontrada.setDisponible(false);

        //suma la recaudacion de la habitacion al hotel
        this.hotel.sumarRecaudacion(habitacionEncontrada.getPrecio());


        //guarda o actualiza la visita
        boolean encontrado = false;
        for (RegistroVisita rv : registroVisitas) {
            if (rv.getIdCliente() == dniCliente && rv.getIdHotel() == hotel.getIdHotel()) {
                rv.setCantidad(rv.getCantidad() + 1);
                rv.setFechaEstadia(fechaEstadia);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            registroVisitas.add(new RegistroVisita(dniCliente, 1, hotel.getIdHotel(), fechaEstadia));
        }

        System.out.println("Check-In realizado con éxito del cliente " + dniCliente + " en la habitación " + idHabitacion + " en la fecha " + fechaEstadia);
    }

    public void checkOut() {
    }
}
