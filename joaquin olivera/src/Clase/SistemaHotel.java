package Clase;

import Enums.MetodoPago;
import Enums.MotivoNoDisponible;
import Enums.ServicioEspecialDeluxe;
import Enums.ServicioEspecialSuite;
import Excepcion.DuplicadoException;
import Excepcion.NoRegistradoException;
import Excepcion.UsuarioNoEncontradoException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class SistemaHotel {
    private ArrayList <Usuario> usuarios;
    private Administracion admin;
    private Recepcionista recepcionista; //un único recepcionista
    private Hotel hotel; //para que no lo cargue administrador
    private Usuario actual;

    public SistemaHotel() {
        this.usuarios = new ArrayList<>();
        this.hotel=new Hotel(1, "Hotel BellaVista ", "Avenida Siempre Viva 742");//crea el hotel
    }

    public SistemaHotel(JSONObject obj) throws JSONException {
        this.usuarios = new ArrayList<>();
        JSONObject hotelJson = obj.getJSONObject("hotel");
        this.hotel = new Hotel(hotelJson);

        // actualizar contador de Habitacion
        int maxIdHab = 0;
        for (Habitacion h : hotel.getHabitaciones().getLista()) {
            if (h.getId() > maxIdHab) maxIdHab = h.getId();
        }
        Habitacion.setContador(maxIdHab + 1);

        JSONArray usuariosJson = obj.getJSONArray("usuarios");
        for (int i = 0; i < usuariosJson.length(); i++) {
            JSONObject usuarioJson = usuariosJson.getJSONObject(i);
            String tipo = usuarioJson.optString("tipo");

            switch (tipo) {

                case "Administrador":
                    Administracion admin = new Administracion(usuarioJson);
                    if (admin.getHotel() == null) admin.setHotel(this.hotel);
                    this.usuarios.add(admin);
                    this.admin = admin;
                    break;


                case "Recepcionista":
                    Recepcionista recep = new Recepcionista(usuarioJson);
                    this.usuarios.add(recep);
                    this.recepcionista = recep;
                    //cargar reservas del recepcionista y actualizar contador
                    JSONArray reservasJson = usuarioJson.optJSONArray("reservas");
                    int maxIdRes = 0;
                    if (reservasJson != null) {
                        for (int j = 0; j < reservasJson.length(); j++) {
                            JSONObject resJson = reservasJson.getJSONObject(j);
                            Reserva r = new Reserva(resJson);
                            if (r.getIdBuscado() > maxIdRes){
                                maxIdRes = r.getIdBuscado();
                            }
                        }
                    }
                    Reserva.setCont(maxIdRes + 1);
                    break;

                case "Cliente":
                    Cliente cliente = new Cliente(usuarioJson);
                    this.usuarios.add(cliente);
                    if (this.recepcionista != null) {
                        this.recepcionista.registrarClienteExistente(cliente);
                    }
                    break;
            }
        }

        for (Usuario u : usuarios) {
            if (u instanceof Recepcionista) {
                Recepcionista r = (Recepcionista) u;
                r.setHotel(this.hotel);
                this.recepcionista = r;
            }
        }
        // asociar recepcionista al administrador (si hay admin y recepcionista)
        if (this.admin != null && this.recepcionista != null) {
            this.admin.setRecepcionista(this.recepcionista);
        }

        if (this.recepcionista != null) {
            ArrayList<Reserva> listaReservas = this.recepcionista.getReservas().getLista();
            for (Reserva r : listaReservas) {
                Cliente cliente = recepcionista.buscarCliente(r.getDniCliente());
                if (cliente != null) {
                    cliente.setReserva(r);
                }
            }
        }
    }




    public Usuario getActual() {
        return actual;
    }

    public Administracion registrarAdministrador(String nombreUsuario, String contrasenia){
        this.admin = new Administracion(nombreUsuario, contrasenia);
        //asignar el hotel creado al administrador
        this.admin.setHotel(this.hotel);
        return this.admin;
    }

    public Recepcionista registrarRecepcionista(String nombreUsuario, String contrasenia) {
        Recepcionista r1 = new Recepcionista(nombreUsuario, contrasenia, this.hotel);
        this.recepcionista=r1;
       //si ya existe administrador, lo guarda ahí también
        if (this.admin!=null){
            this.admin.setRecepcionista(r1);
        }
        return this.recepcionista;
    }

    public void registrarUsuario(String nombreUsuario, String contrasenia, int opcion) throws DuplicadoException {

        //verificar si existe el usuario
        for (Usuario u : usuarios){
            if (u.getNombreUsuario().equals(nombreUsuario)){
                System.out.println("Error: ya existe un usuario con ese nombre");
                return;
            }
        }
        Usuario nuevo = null;
        switch (opcion) {
            case 1: // Administrador
                if (this.admin!=null){
                    throw new DuplicadoException("Ya existe un administrador registrado");
                }
                nuevo=registrarAdministrador(nombreUsuario, contrasenia);
                break;
            case 2: // recepcionista
                if (this.recepcionista!=null){
                    throw new DuplicadoException("Ya existe un recepcionista registrado");
                }
                nuevo=registrarRecepcionista(nombreUsuario, contrasenia);
                break;
            case 3: // cliente
                nuevo=new Cliente(nombreUsuario, contrasenia);
                break;
            default:
                System.out.println("Opción inválida. No se registró ningún usuario.");
                break;
        }
        usuarios.add(nuevo);
        pasarAJSONaArchivo();
        System.out.println("Usuario registrado con éxito");
    }

    public String iniciarSesion(String nombreUsuario, String contrasenia) throws UsuarioNoEncontradoException {
        for (Usuario u: usuarios){
            if (u.validarUsuario(nombreUsuario, contrasenia)){
                this.actual=u; //guarda quien está logueado
                return u.getTipo();
            }
        }
        throw new UsuarioNoEncontradoException("usuario o contraseña incorrecta");
    }


    public String mostrarHotel()
    {
        String rta="";
        if (this.hotel==null){
            rta="No existe el hotel";
        }
        rta=this.hotel.toString();
        return rta;
    }

    public String verRecepcionista() {
        String rta = "";
        try {
            rta=admin.mostrarRecepcionista();
        } catch (NoRegistradoException e) {
            System.out.println(e.getMessage());
        }
        return rta;
    }

    //cargar y muestra habitaciones
    public void cargarHabitacionEstandar( String descripcion, int personasPermitidas, boolean estado, MotivoNoDisponible motivoNoDisponible) {
      try {
          admin.agregarHabitacionEstandar( descripcion, personasPermitidas, estado, motivoNoDisponible);
          pasarAJSONaArchivo();
      }catch (NoRegistradoException e){
          System.out.println(e.getMessage());
      }
    }

    public void cargarHabitacionSuite( String descripcion, int personasPermitidas, ServicioEspecialSuite especialSuite, boolean disponible, MotivoNoDisponible motivoNoDisponible) {
       try {
           admin.agregarHabitacionSuite( descripcion, personasPermitidas, especialSuite, disponible, motivoNoDisponible);
           pasarAJSONaArchivo();
       }catch (NoRegistradoException e){
           System.out.println(e.getMessage());
       }
    }

    public void cargarHabitacionDeluxe( String descripcion, int personasPermitidas, ServicioEspecialDeluxe servicioEspecialDeluxe, boolean disponible, MotivoNoDisponible motivoNoDisponible) {
       try {
           admin.agregarHabitacionDeluxe(descripcion, personasPermitidas, servicioEspecialDeluxe, disponible,  motivoNoDisponible);
           pasarAJSONaArchivo();
       }catch (NoRegistradoException e){
           System.out.println(e.getMessage());
       }
    }

    public boolean eliminarHabitacion(int idHabitacion){
        boolean eliminado= admin.eliminarHabitacion(idHabitacion);
        if (eliminado){
            pasarAJSONaArchivo();
        }
        return eliminado;
    }

    public String mostrarHabitacion(int idHabitacion) {
        String rta = "";
        try {
            rta=admin.getHotel().mostrarHabitacion(idHabitacion);
        } catch (NoRegistradoException e) {
            System.out.println(e.getMessage());
        }
        return rta;
    }

    public String mostrarHabitaciones(){
        String rta="";
        if (this.hotel==null){
            rta="No existe el hotel";
        }
        rta=this.hotel.mostrarHabitaciones();
        return rta;
    }

    //recepcionista metodos
    public void checkIn(int dniCliente, int idReserva){
        try {
            recepcionista.checkIn(dniCliente, idReserva);
            pasarAJSONaArchivo();
        }catch (NoRegistradoException e){
            System.out.println(e.getMessage());
        }
    }

    public void checkOut(int idReserva, int dniCliente){
        try {
            recepcionista.checkOut(idReserva, dniCliente);
            pasarAJSONaArchivo();
        } catch (NoRegistradoException e) {
            System.out.println(e.getMessage());
        }
    }

    public String consultarDisponibilidad(){
            if (recepcionista == null) {
                return "No hay recepcionista registrado.";
            }
            return recepcionista.consultarDisponibilidad();
    }

    public String buscarReserva(int id){
        String rta="";
        Reserva r1=recepcionista.buscarReserva(id);
        if (r1==null){
            rta="No se encontro ninguna reserva con el id: "+id;
        }else {
            rta=r1.toString();
        }
        return rta;
    }

    public String buscarCliente(int dni){
        String rta="";
        Cliente c1= recepcionista.buscarCliente(dni);
       if (c1==null){
           rta="No se encontro ningun cliente con el dni: "+dni;
       }else {
           rta=c1.toString();
       }
       return rta;
    }

    //metodos cliente
    public String hacerReserva(int idHabitacion, String fechaInicio, String fechaSalida, MetodoPago metodoPago) throws NoRegistradoException{
        String rta="";
        try {
            if (!(actual instanceof Cliente)){
                return "El usuario actual no es un cliente";
            }
            Cliente c1= (Cliente) actual; //cliente logueado

            if (recepcionista==null){
                throw new NoRegistradoException("no hay recepcionista disponible") ;
            }

            if (hotel==null){
               throw new NoRegistradoException("no hay hotel asociado");
            }
            c1.setHotel(hotel);
            c1.setMetodoPago(metodoPago);

            Habitacion habitacion = buscarHabitacion(idHabitacion);
            if (habitacion == null){
                return "Error: No existe la habitación con id " + idHabitacion;
            }

            rta= c1.hacerReserva(idHabitacion, recepcionista, fechaInicio, fechaSalida);

            pasarAJSONaArchivo();
            return rta;

        }catch (NoRegistradoException e){
            return e.getMessage();
        }
    }

    public Habitacion buscarHabitacion(int idHabitacion){
        return hotel.buscarHabitacion(idHabitacion);
    }

    //metodo para calcular la recaudacion y que el administrador pueda ver el total
    public String mostrarRecaudacion() {
        String rta="";
        if (admin == null) {
            rta="No hay administrador registrado.";
        }else if (hotel == null) {
            rta ="No hay hotel cargado.";
        }
        else
        {
            rta="Recaudación total del hotel: $" + hotel.verRecaudacion();

        }
        return rta;
    }


    public String completarDatosCliente(String nombre, int dni, String domicilio) throws NoRegistradoException{
        if (actual instanceof Cliente){
            Cliente c1= (Cliente) actual;

            if (recepcionista==null){
                throw new NoRegistradoException("No hay recepcionista registrado.");
            }


            Cliente existente=recepcionista.buscarCliente(dni);
            if (existente!=null && existente!=c1){
                throw new NoRegistradoException("Ya existe un cliente registrado con ese dni. Ingrese los datos nuevamente...");
            }


            c1.setHotel(hotel);
            c1.setNombre(nombre);
            c1.setDni(dni);
            c1.setDomicilio(domicilio);
            recepcionista.registrarClienteExistente(c1);
            pasarAJSONaArchivo();
            return "Datos del cliente guardados correctamente.";
        }else {
            return "El usuario actual no es un cliente";
        }
    }

    public String verMisReservas(){
        if (!(actual instanceof Cliente)){
            return "El usuario actual no es un cliente";
        }
        Cliente c1= (Cliente) actual;
        return c1.verMisReservas(recepcionista);
    }

    public String mostrarClientes()
    {
        return recepcionista.mostrarClientes();
    }

    public String mostrarReservas()
    {
        return recepcionista.mostrarReservas();
    }

    public String verHabitacionesNoDisponiblesPorMotivo(){
        return recepcionista.verHabitacionesNoDisponiblesPorMotivo();
    }

    public String verHabitacionesOcupadas(){
        return recepcionista.verHabitacionesOcupadas();
    }


    public String generarDetalleReserva(int idHabitacion, LocalDate fechaInicio, LocalDate fechaSalida){
        Habitacion h1=admin.getHotel().buscarHabitacion(idHabitacion);
        if (h1 == null){
            return "La habitacion no existe";
        }
        long noches= ChronoUnit.DAYS.between(fechaInicio, fechaSalida);
        double totalReserva=noches*h1.getPrecio();

        return "\n===== DETALLE DE LA RESERVA =====\n" +
                "Habitacion ID: " + idHabitacion + "\n" +
                "Fecha ingreso: " + fechaInicio + "\n" +
                "Fecha salida: " + fechaSalida + "\n" +
                "Noches: " + noches + "\n" +
                "Precio por noche: $" + h1.getPrecio() + "\n" +
                "TOTAL: $" + totalReserva + "\n" +
                "=================================\n";
    }


    public void pasarAJSONaArchivo() {
        JSONObject obj = new JSONObject();
        try {

            if (hotel != null) {
                obj.put("hotel", hotel.toJSON());
            } else {
                obj.put("hotel", JSONObject.NULL);
            }

            JSONArray usuariosArray = new JSONArray();
            for (Usuario u : usuarios) {
                usuariosArray.put(u.toJson());
            }
            obj.put("usuarios", usuariosArray);
            JsonUtiles.subirArchivoObj(obj);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }




    @Override
    public String toString() {
        return "SistemaHotel{" +
                "usuarios=" + usuarios +
                ", admin=" + admin +
                ", recepcionista=" + recepcionista +
                ", hotel=" + hotel +
                ", actual=" + actual +
                '}';
    }
}
