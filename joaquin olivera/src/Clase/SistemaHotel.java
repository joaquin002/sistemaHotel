package Clase;

import Enums.MetodoPago;
import Enums.MotivoNoDisponible;
import Enums.ServicioEspecialDeluxe;
import Enums.ServicioEspecialSuite;
import Excepcion.DuplicadoException;
import Excepcion.NoRegistradoException;
import Excepcion.UsuarioNoEncontradoException;
import org.json.JSONArray;
import org.json.JSONObject;

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

    public SistemaHotel(JSONObject obj) {
        JSONArray usuarios=obj.getJSONArray("usuarios");
        for (int i = 0; i < usuarios.length(); i++) {
            JSONObject usuario=usuarios.getJSONObject(i);
            this.usuarios.add(new Usuario(usuario));
        }
        JSONObject admin=obj.getJSONObject("admin");
        JSONObject recepcionista=obj.getJSONObject("recepcionista");
        JSONObject hotel=obj.getJSONObject("hotel");
        this.admin=new Administracion(admin);
        this.recepcionista=new Recepcionista(recepcionista);
        this.hotel=new Hotel(hotel);
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
        int id=1;
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
    public void cargarHabitacionEstandar(double precio, String descripcion, String servicios, int personasPermitidas, boolean estado, MotivoNoDisponible motivoNoDisponible) {
      try {
          admin.agregarHabitacionEstandar(precio, descripcion, servicios, personasPermitidas, estado, motivoNoDisponible);
      }catch (NoRegistradoException e){
          System.out.println(e.getMessage());
      }
    }

    public void cargarHabitacionSuite(double precio, String descripcion, String servicios, int personasPermitidas, ServicioEspecialSuite especialSuite, boolean disponible, MotivoNoDisponible motivoNoDisponible) {
       try {
           admin.agregarHabitacionSuite(precio, descripcion, servicios, personasPermitidas, especialSuite, disponible, motivoNoDisponible);
       }catch (NoRegistradoException e){
           System.out.println(e.getMessage());
       }
    }

    public void cargarHabitacionDeluxe(double precio, String descripcion, String servicios, int personasPermitidas, ServicioEspecialDeluxe servicioEspecialDeluxe, boolean disponible, MotivoNoDisponible motivoNoDisponible) {
       try {
           admin.agregarHabitacionDeluxe( precio, descripcion, servicios, personasPermitidas, servicioEspecialDeluxe, disponible,  motivoNoDisponible);
       }catch (NoRegistradoException e){
           System.out.println(e.getMessage());
       }
    }

    public boolean eliminarHabitacion(int idHabitacion){
       return admin.eliminarHabitacion(idHabitacion);
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
        }catch (NoRegistradoException e){
            System.out.println(e.getMessage());
        }
    }

    public void checkOut(int idReserva, int dniCliente){
        try {
            recepcionista.checkOut(idReserva, dniCliente);
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
    public String hacerReserva(int idHabitacion, String fechaInicio, String fechaSalida) throws NoRegistradoException{
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

            return c1.hacerReserva(idHabitacion, recepcionista, fechaInicio, fechaSalida);

        }catch (NoRegistradoException e){
            return e.getMessage();
        }
    }

    public String completarDatosCliente(String nombre, int dni, String domicilio, MetodoPago metodoPago) throws NoRegistradoException{
        if (actual instanceof Cliente){
            Cliente c1= (Cliente) actual;
            c1.setHotel(hotel);
            c1.setNombre(nombre);
            c1.setDni(dni);
            c1.setDomicilio(domicilio);
            c1.setMetodoPago(metodoPago);
            if (recepcionista==null){
                throw new NoRegistradoException("no hay recepcionista registrado.");
            }
            recepcionista.registrarClienteExistente(c1);
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
    public void toJSON(String nomrbeArchivo){
        JSONObject admin = new JSONObject();
        admin.put("administrador", this.admin.toJSON());
        //JsonUtiles.subirJsonObject(admin);
       // JsonUtiles.subirArchivoObj(admin);
        JSONArray lista = new JSONArray();
        for (Usuario u : usuarios){
            lista.put(u.toJson());
        }
        //JsonUtiles.subirArchivo(lista);
        //JsonUtiles.subirArchivo(lista);
    }

    public void pasarAJSONaArchivo(String nombreArchivo) {
        JSONObject obj = new JSONObject();
        try {
            //hotel
            if (hotel != null)
                obj.put("hotel", hotel.toJSON());

            //administrador
            if (admin != null)
                obj.put("admin", admin.toJSON());

            //recepcionista
            if (recepcionista != null){
                obj.put("recepcionista", recepcionista.toJson());
            } else{
                obj.put("recepcionista", JSONObject.NULL);
            }

            //usuarios
            JSONArray usuariosArray = new JSONArray();
            for (Usuario u : usuarios) {
                usuariosArray.put(u.toJson());
            }
            obj.put("usuarios", usuariosArray);
            JsonUtiles.subirArchivoObj(nombreArchivo, obj);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    public String mostrarArchivo(String nombreArchivo){
        return JsonUtiles.descargarJson(nombreArchivo);
    }
}
