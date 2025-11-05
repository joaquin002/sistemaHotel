package Clase;

public abstract class Usuario {
    private String nombreUsuario;
    private String contrasenia;
    private int tipo;

    public Usuario(String nombreUsuario, String contrasenia, int tipo) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.tipo = tipo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public int getTipo() {
        return tipo;
    }

    public boolean validarUsuario(String nombreU, String contrasenia) {
        return this.nombreUsuario.equals(nombreU) && this.contrasenia.equals(contrasenia);
    }

    @Override
    public String toString() {
        return "Clase.Usuario{" +
                "nombreUsuario='" + nombreUsuario + '\'' +
                ", contraseña='" + contrasenia + '\'' +
                '}';
    }
}
