import java.util.ArrayList;

public class Generica <T>{
    //cambiar de nombre la clase
    private ArrayList<T>listado; //para objetos que se puedan repetir

    public Generica() {
        this.listado = new ArrayList<>();
    }

    public boolean agregarEnListado(T obj){
        return this.listado.add(obj);
    }

    public boolean eliminarHabitacion(int buscado){
        for (T aux : listado){
            if (aux instanceof Habitacion){
                if(((Habitacion) aux).getId() == buscado){
                    return this.listado.remove(aux);
                }
            }
        }
        return false;
    }

    public String mostrarHabitacion(int idBuscado){
        String info="";
        for (T aux : listado){
            if (aux instanceof Habitacion){
                if (((Habitacion) aux).getId() == idBuscado){
                    info = aux.toString();
                    return info;
                }
            }
        }
        info = "no se encontro la habitacion";
        return info;
    }

}
//mi idea es que haya métodos generales como el de agregar en lista y que después haya métodos más personalizados.