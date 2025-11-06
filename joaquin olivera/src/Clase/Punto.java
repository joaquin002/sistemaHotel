package Clase;

public class Punto {
    private int id;
    private int dniCliente;
    private int cantidad;

    public Punto(int id, int dniCliente, int cantidad) {
        this.id = id;
        this.dniCliente = dniCliente;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public int getDniCliente() {
        return dniCliente;
    }

    public int getCantidad() {
        return cantidad;
    }

    @Override
    public String toString() {
        return "Punto{" +
                "id=" + id +
                ", dniCliente=" + dniCliente +
                ", cantidad=" + cantidad +
                '}';
    }
}
