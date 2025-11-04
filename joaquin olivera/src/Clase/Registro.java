package Clase;

import java.util.ArrayList;
import java.util.HashSet;

public class Registro<T> {
    private ArrayList <T> lista;


    public Registro() {
        lista = new ArrayList();
    }

    // agregar
    public void agregar(T elemento) {
        lista.add(elemento);
    }
    //eliminar
    public boolean eliminar(T elemento) {
        return lista.remove(elemento);
    }
    //mostrar
    public String mostrar(){
        String rta="";
        for (T elemento : lista) {
            rta+=elemento.toString()+"\n";
        }
        return rta;
    }
}
