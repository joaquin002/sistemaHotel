package Clase;

import Excepcion.NoRegistradoEx;

import java.util.ArrayList;

public class Recepcionista extends Usuario {
    private int id;
    private int idHotel;
    private Registro<Cliente> clientes;
    private Registro<Reserva> reservas;
    private ArrayList<Punto> puntos;
    private ArrayList<RegistroVisita> registroVisitas;
    private Hotel hotel;

    //para parte de usuario
    public Recepcionista(String nombreUsuario, String contrasenia) {
        super(nombreUsuario, contrasenia);
    }

    public Recepcionista(String nombreUsuario, String contrasenia, int id, int idHotel) {
        super(nombreUsuario, contrasenia);
        this.id = id;
        this.idHotel = idHotel;
        this.clientes = new Registro<>();
        this.reservas = new Registro<>();
        this.puntos = new ArrayList<>();
        this.registroVisitas = new ArrayList<>();
        this.hotel = hotel;
    }


    public void checkIn(int dniCliente, int idHabitacion, String fechaEstadia) throws NoRegistradoEx {
        //verifica cliente
        if (!clientes.buscarPorId(dniCliente)) {
            throw new NoRegistradoEx("el cliente con dni " + dniCliente + " no esta registrado.");
        }

        //verifica habitación
        Habitacion habitacionEncontrada = null;
        for (Habitacion h : hotel.getHabitaciones().getLista()) {
            if (h.getIdBuscado() == idHabitacion) {
                habitacionEncontrada = h;
                break;
            }
        }
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

        //guarda o actualiza la visita
        boolean encontrado = false;
        for (RegistroVisita rv : registroVisitas) {
            if (rv.getIdCliente() == dniCliente && rv.getIdHotel() == idHotel) {
                rv.setCantidad(rv.getCantidad() + 1);
                rv.setFechaEstadia(fechaEstadia);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            registroVisitas.add(new RegistroVisita(dniCliente, 1, idHotel, fechaEstadia));
        }

        System.out.println("Check-In realizado con éxito del cliente " + dniCliente + " en la habitación " + idHabitacion + " en la fecha " + fechaEstadia);
    }

    public void checkOut() {
    }
}
