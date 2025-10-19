public class Conserje extends Usuario{
    private boolean estadoHabitacion; //true ocupada false desocupada
    private boolean limpieza; //true limpio false: no se limpio
    private boolean reparacion; // true se reparo false no se reparo
    private double tarifaAdicional;

    //constructor para usuario
    public Conserje(String nombreUsuario, String contrasenia) {
        super(nombreUsuario, contrasenia);
        this.estadoHabitacion = estadoHabitacion;
    }

    public Conserje(String nombreUsuario, String contrasenia, boolean estadoHabitacion, boolean limpieza, boolean reparacion, double tarifaAdicional) {
        super(nombreUsuario, contrasenia);
        this.estadoHabitacion = estadoHabitacion;
        this.limpieza = limpieza;
        this.reparacion = reparacion;
        this.tarifaAdicional = tarifaAdicional;
    }

    @Override
    public String toString() {
        return "Conserje{" +
                "estadoHabitacion=" + estadoHabitacion +
                ", limpieza=" + limpieza +
                ", reparacion=" + reparacion +
                ", tarifaAdicional=" + tarifaAdicional +
                '}';
    }
}
