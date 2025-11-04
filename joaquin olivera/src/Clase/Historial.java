package Clase;

public class Historial {
    private int dniCliente;
    private String fechaEstadia; // con formato

    public Historial(int dniCliente, String fechaEstadia) {
        this.dniCliente = dniCliente;
        this.fechaEstadia = fechaEstadia;
    }

    @Override
    public String toString() {
        return "Historial{" +
                "dniCliente=" + dniCliente +
                ", fechaEstadia='" + fechaEstadia + '\'' +
                '}';
    }
}
