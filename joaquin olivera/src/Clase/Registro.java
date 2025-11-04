package Clase;

import Interfaces.Identificable;

import java.util.ArrayList;
import java.util.HashSet;

public class Registro<T extends Identificable> {
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
    //buscar
    public T buscar(int idBuscado) {
        for (T elemento : lista) {
            if (elemento.getIdBuscado() == idBuscado) {
                return elemento;
            }
        }
        return null;
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
