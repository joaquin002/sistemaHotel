package Clase;

import Enums.EcantPersonas;
import Enums.EtipoHabitacion;

public class Administracion extends Usuario {

    private Generica<Habitacion> catalogo; //atributo habitaciones
    // definir próximos atributos

    public Administracion(String nombreUsuario, String contrasenia, Generica<Habitacion> catalogo) {
        super(nombreUsuario, contrasenia);
        this.catalogo = new Generica<>();
    }
    //modificar constructor con próximos atributos

    //constructor para usuario
    public Administracion(String nombreUsuario, String contrasenia) {
        super(nombreUsuario, contrasenia);
        this.catalogo = new Generica<>();
    }

    //getters
    public Generica<Habitacion> getCatalogo() {
        return catalogo;
    }


    /// metodos admin
    // metodos de habitaciones:
    public boolean agregarHabitacion (int precio, String descripcion, EtipoHabitacion tipo, EcantPersonas cantPersonas, String servicios, boolean estado){ //este metodo es de prueba
        Habitacion H = new Habitacion(precio, descripcion, tipo, cantPersonas, servicios, estado);
        return this.catalogo.agregarEnListado(H);
    }
    public boolean borrarHabitacion(int idBuscado){
        return this.catalogo.eliminarHabitacion(idBuscado);
    }
    public String muestraHabitacion(int idBuscado){
        String info = this.catalogo.mostrarHabitacion(idBuscado);
        return info;
    }
    //faltan mas

    //los generales
    public boolean agregarHabitacion2(int precio, String descripcion, EtipoHabitacion tipo, EcantPersonas cantPersonas, String servicios, boolean estado){
        Habitacion h1=new Habitacion(precio, descripcion, tipo, cantPersonas, servicios, estado);
        return catalogo.agregar(h1);
    }


    public String verHabitacion(){
        return catalogo.mostrarTodo();
    }

}
