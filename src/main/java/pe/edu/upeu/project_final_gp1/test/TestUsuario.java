package pe.edu.upeu.project_final_gp1.test;

import pe.edu.upeu.project_final_gp1.implementacion.UsuarioDaoImpl;
import pe.edu.upeu.project_final_gp1.interfaces.IUsuarioDao;
import pe.edu.upeu.project_final_gp1.modelo.Usuario;

public class TestUsuario {
    private static IUsuarioDao dao = new UsuarioDaoImpl();
    public void valid_login(){
        Usuario u = dao.login("admin", "admin123");
        if (u != null){
            System.out.println("ID-Usuaruio " + u.getIdusuario());
            System.out.println("NOMBRE " + u.getNombres());
            System.out.println("USUARIO " + u.getUser());
            System.out.println("TIPO-USUARIO " + u.getTipoUsuario());

        }else {
            System.out.println("error login");
        }
    }

    public static void main(String[] args) {
        TestUsuario t = new TestUsuario();
        t.valid_login();
    }

}
