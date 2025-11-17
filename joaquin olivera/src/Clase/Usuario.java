package Clase;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class Usuario {
    private String nombreUsuario;
    private String contrasenia;
    private String tipo;

    public Usuario(String nombreUsuario, String contrasenia, String tipo) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.tipo = tipo;
    }
    public Usuario(){

    }
    public Usuario(JSONObject obj) throws JSONException {
        this.nombreUsuario = obj.getString("nombreUsuario");
        this.contrasenia = obj.getString("contrasenia");
        this.tipo = obj.getString("tipo");
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getTipo() {
        return tipo;
    }

    public boolean validarUsuario(String nombreU, String contraseniaIngresada) {
        return this.nombreUsuario.equals(nombreU) && this.contrasenia.equals(contraseniaIngresada);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombreUsuario='" + nombreUsuario + '\'' +
                ", contraseña='" + contrasenia + '\'' +
                '}';
    }
    public JSONObject toJson(){
        JSONObject json = new JSONObject();
        try{
            json.put("nombreUsuario", this.nombreUsuario);
            json.put("contrasenia", this.contrasenia);
            json.put("tipo", this.tipo);
        }catch (JSONException e){
            e.printStackTrace();
        }
        return json;
    }
}
