
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Generica <T> {
    //cambiar de nombre la clase
    private ArrayList<T>listado; //para objetos que se puedan repetir
    private HashSet<T> litaPrueba;


    public Generica() {
        this.listado = new ArrayList<>();
        this.litaPrueba=new HashSet<>();
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


    //generales
    public boolean agregar(T elemento){
        return listado.add(elemento);
    }
/*
    public boolean eliminar(int id){
       boolean encontrar=false;
        for (T e : listado){
           if (e){
               encontrar=true;
               listado.remove(e);
           }
       }
        return encontrar;
    }


    public T buscar(T elemento){
        T rta = null;
        for (T e: listado){
            if (e.equals(elemento)){
                rta= e;
            }
        }
        return rta;
    }

 */
    public String mostrarTodo(){
        String rta="";
        for (T e: listado){
            rta+=e.toString();
        }
        return rta;
    }

}
//mi idea es que haya métodos generales como el de agregar en lista y que después haya métodos más personalizados.