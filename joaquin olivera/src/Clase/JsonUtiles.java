package Clase;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;

public class JsonUtiles {
    public static final String archivo="SistemaHotel";
    public static void subirArchivoObj(JSONObject jsonObject){
        try{
            BufferedWriter salida = new BufferedWriter(new FileWriter(archivo+".json"));
            salida.write(jsonObject.toString());
            salida.flush();
            salida.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }


        public static String descargarJson(){
            StringBuilder contenido = new StringBuilder();
            String lectura= "";
            try
            {
                BufferedReader entrada = new BufferedReader(new FileReader(archivo+".json"));
                while((lectura = entrada.readLine())!=null){
                    contenido.append(lectura);
                }
                entrada.close();
            }
            catch (IOException e)
            {
                System.out.println(e.getMessage());
            }
            return contenido.toString();
        }
}
