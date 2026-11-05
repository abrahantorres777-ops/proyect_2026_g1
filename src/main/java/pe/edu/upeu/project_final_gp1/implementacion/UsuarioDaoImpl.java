package pe.edu.upeu.project_final_gp1.implementacion;

import pe.edu.upeu.project_final_gp1.interfaces.IUsuarioDao;
import pe.edu.upeu.project_final_gp1.modelo.Usuario;
import pe.edu.upeu.project_final_gp1.utils.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDaoImpl implements IUsuarioDao {

    @Override
    public Usuario login(String user, String password) {
        Usuario us = null;
        String query= "SELECT * FROM usuario WHERE user = ? AND password= ?;";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement st = cn.prepareStatement(query)
        ) {
            st.setString(1,user);
            st.setString(2,password);
            try (ResultSet rs = st.executeQuery()){
                if (rs.next()){
                    us = new Usuario();
                    us.setIdusuario(rs.getInt("idusuario"));
                    us.setUser(rs.getString("user"));
                    us.setTipoUsuario(rs.getString("tipoUser"));
                    us.setNombres(rs.getString("nombre"));
                    us.setApellidos(rs.getString("apellidos"));
                    us.setDni(rs.getString("dni"));
                    System.out.println("login success");

                }

            }

        } catch (SQLException e) {
            System.out.println("Error de login"+e.getMessage());
        }
        return us;
    }

    @Override
    public boolean insert_user(Usuario usuario) {
        return false;
    }
}
