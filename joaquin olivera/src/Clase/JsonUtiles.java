package Clase;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;

public class JsonUtiles {
    private static final String archivo = "archivoJson";
        public static void subirJSonArray(JSONArray jsonArray){
            try{
                BufferedWriter salida = new BufferedWriter(new FileWriter(archivo+".json"));
                salida.write(jsonArray.toString());
                salida.flush();
                salida.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        public static void subirJsonObject(JSONObject jsonObject){
            try{
                BufferedWriter salida = new BufferedWriter(new FileWriter(archivo+".json"));
                salida.write(jsonObject.toString());
                salida.flush();
                salida.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        public static String descargarJson(String archive){
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
                e.printStackTrace();
            }


            return contenido.toString();
        }


}
