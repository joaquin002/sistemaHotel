public class Conserje{
    private boolean estadoHabitacion; //true ocupada false desocupada
    private boolean limpieza; //true limpio false: no se limpio
    private boolean reparacion; // true se reparo false no se reparo
    private double tarifaAdicional;

    public Conserje(boolean estadoHabitacion, boolean limpieza, boolean reparacion, double tarifaAdicional) {
        this.estadoHabitacion = estadoHabitacion;
        this.limpieza = limpieza;
        this.reparacion = reparacion;
        this.tarifaAdicional = tarifaAdicional;
    }

    @Override
    public String toString() {
        return super.toString()+"Conserje{" +
                "estadoHabitacion=" + estadoHabitacion +
                ", limpieza=" + limpieza +
                ", reparacion=" + reparacion +
                ", tarifaAdicional=" + tarifaAdicional +
                '}';
    }
}
