package pe.edu.upeu.project_final_gp1.interfaces;

import pe.edu.upeu.project_final_gp1.modelo.Usuario;

public interface IUsuarioDao {
        public Usuario login(String user, String password);
        public boolean insert_user(Usuario usuario);


    }
