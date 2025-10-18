public class Administracion {
    private Generica<Habitacion> catalogo; //atributo habitaciones
    // definir próximos atributos

    public Administracion() {
        this.catalogo = new Generica<>();
    }//modificar constructor con próximos atributos

    //getters
    public Generica<Habitacion> getCatalogo() {
        return catalogo;
    }

    /// metodos admin
    // metodos de habitaciones:
    public boolean agregarHabitacion (int precio, String descripcion, EtipoHabitacion tipo, EcantPersonas cantPersonas, String servicios, Eestado estado){ //este metodo es de prueba
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


}
