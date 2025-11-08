package Clase;

import Enums.MetodoPago;
import Excepcion.DuplicadoEx;
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
    //ver private IhotelOperable hotel; //una variable de tipo interface que solo te deja utilizar los metodos elegidos
    private Hotel hotel;

    //para parte de usuario
    public Recepcionista(int id, String nombreUsuario, String contrasenia) {
        super(nombreUsuario, contrasenia, "Recepcionista");
        this.id = id;
        this.clientes=new Registro<>();
        this.reservas=new Registro<>();
        this.puntos=new ArrayList<>();
    }

    public Recepcionista(int id, String nombreUsuario, String contrasenia, Hotel hotel) {
        super(nombreUsuario, contrasenia, "Recepcionista");
        this.id = id;
        this.hotel = hotel;
        this.clientes=new Registro<>();
        this.reservas=new Registro<>();
        this.puntos=new ArrayList<>();
    }

    public Recepcionista(int id, Hotel hotel) {
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

    public Cliente registrarClientes(String nombre, int dni, String domicilio, MetodoPago metodoPago) {
        Cliente c1 = new Cliente(nombre, dni, domicilio, metodoPago);
        c1.setHotel(this.hotel);
        clientes.agregar(c1);
        return c1;
    }

    //busca una reserva
    public Reserva buscarReserva(int id) {
        return this.reservas.buscar(id);
    }

    public Cliente buscarCliente(int dni) {
        return this.clientes.buscar(dni);
    }

    public void checkIn(int dniCliente, int idHabitacion, String fechaEstadia, String nombre, String domicilio, MetodoPago metodoPago) throws NoRegistradoEx {

        //verifica si el cliente ya existe
        Cliente c1 = buscarCliente(dniCliente);
        if (c1 == null) {
            //registra el cliente
            c1 = registrarClientes(nombre, dniCliente, domicilio, metodoPago);
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
        Reserva nuevaReserva = new Reserva(dniCliente, this.id, fechaEstadia, idHabitacion);
        reservas.agregar(nuevaReserva);

        //asignar la reserva al cliente
        c1.agregarReserva(nuevaReserva);


        //marca la habitacion como ocupada
        habitacionEncontrada.setDisponible(false);

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

    public void checkOut(int idReserva, int dniCliente, String fechaSalida) throws NoRegistradoEx {
        Reserva r2 = buscarReserva(idReserva); //buscar la reserva
        if (r2 == null) {
            throw new NoRegistradoEx("la reserva con id: " + idReserva + " no esta registrada");
        }

        //buscar habitacion de la reserva
        Habitacion h1 = hotel.buscarHabitacion(r2.getIdHabitacion());
        if (h1 == null) {
            throw new NoRegistradoEx("no se encontro la habitacion asociada a la reserva");
        }

        //liberar habitacion
        h1.setDisponible(true);

        //sumar recaudacion
        this.hotel.sumarRecaudacion(h1.getPrecio());

        //para que se guarde en el historial
        Cliente c1 = buscarCliente(dniCliente);
        if (c1 == null) {
            throw new NoRegistradoEx("no se encontro el cliente con dni: " + dniCliente);
        }
        c1.guardarHistorial(dniCliente, fechaSalida);

        System.out.println("se realizo con exito el check-out de la reserva " + idReserva + ". Habitación " + h1.getIdBuscado() + " liberada y recaudación actualizada.");
    }

    public int getIdHotel() {
        return this.hotel.getIdHotel();
    }

    public void agregarCliente(String nombre, int dni, String domicilio, MetodoPago metodoPago) throws DuplicadoEx {
        for (Cliente c : this.clientes.getLista()) {
            if (c.getDni() == dni) {
                throw new DuplicadoEx("cliente ya existente");
            }
        }
        this.clientes.agregar(new Cliente(nombre, dni, domicilio, metodoPago));
    }


    @Override
    public String toString() {
        return "Recepcionista{" +
                "id=" + id +
                ", clientes=" + clientes +
                ", reservas=" + reservas +
                ", puntos=" + puntos +
                ", registroVisitas=" + registroVisitas +
                ", hotel=" + hotel.toString() +
                '}';
    }
}