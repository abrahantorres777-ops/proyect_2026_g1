package pe.edu.upeu.project_final_gp1.modelo;

public class UserSesion {
    private static UserSesion instance;
    private Usuario userlogger;

    private UserSesion() {
    }

    public static UserSesion getInstance() {
        if (instance == null) {
            instance = new UserSesion();
        }
        return instance;
    }

    public Usuario getUserlogger() {
        return userlogger;
    }

    public void setUserlogger(Usuario usuario) {
        this.userlogger = usuario;
    }

    public void cleanSession() {
        userlogger = null;
    }

}
