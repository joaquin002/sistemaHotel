package Clase;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;

public class JsonUtiles {

        public static void subirJSonArray(JSONArray jsonArray, String archive){
            try{
                BufferedWriter salida = new BufferedWriter(new FileWriter(archive+".txt"));
                salida.write(jsonArray.toString());
                salida.flush();
                salida.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }

        public static void subirJsonObject(JSONObject jsonObject, String archive){
            try{
                BufferedWriter salida = new BufferedWriter(new FileWriter(archive+".txt"));
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
                BufferedReader entrada = new BufferedReader(new FileReader(archive+".txt"));
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
