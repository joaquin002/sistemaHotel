package Clase;

import Interfaces.Identificable;

import java.util.ArrayList;

public class Registro<T extends Identificable> {
    private ArrayList <T> lista;


    public Registro() {
        lista = new ArrayList<>();
    }

    public ArrayList<T> getLista() {
        return lista;
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

    //mostrar por id
    public String mostrarPorId(int idBuscado){
        String rta="";
        for (T elemento : lista) {
            if (elemento.getIdBuscado() == idBuscado) {
                rta=elemento.toString()+"\n";
            }
        }
        return rta;
    }

    //buscar por id
    public boolean buscarPorId(int idBuscado){
        boolean encontrado=false;
        for (T elemento : lista) {
            if (elemento.getIdBuscado() == idBuscado) {
                encontrado=true;
            }
        }
        return encontrado;
    }
}
