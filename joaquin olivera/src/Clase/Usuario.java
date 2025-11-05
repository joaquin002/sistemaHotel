package Clase;

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

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public String getTipo() {
        return tipo;
    }

    public boolean validarUsuario(String nombreU, String contraseniaIngresada) {
        return this.nombreUsuario.equals(nombreU) && this.contrasenia.equals(contraseniaIngresada);
    }

    @Override
    public String toString() {
        return "Clase.Usuario{" +
                "nombreUsuario='" + nombreUsuario + '\'' +
                ", contraseña='" + contrasenia + '\'' +
                '}';
    }
}
