package Clase;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;

public class JsonUtiles {

    public static void subirArchivoObj(String nombreArchivo, JSONObject jsonObject){
        try{
            BufferedWriter salida = new BufferedWriter(new FileWriter(nombreArchivo+".json"));
            salida.write(jsonObject.toString());
            salida.flush();
            salida.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }


        public static String descargarJson(String nombreArchivo){
            StringBuilder contenido = new StringBuilder();
            String lectura= "";
            try
            {
                BufferedReader entrada = new BufferedReader(new FileReader(nombreArchivo+".json"));
                while((lectura = entrada.readLine())!=null){
                    contenido.append(lectura);
                }
                entrada.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }


            return contenido.toString();
        }


}
