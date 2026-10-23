package pe.edu.upeu.project_final_gp1.test;

import pe.edu.upeu.project_final_gp1.utils.ConexionBD;

import java.sql.Connection;

public class TestConexion {

    public static void testConexion(){
        try {
            Connection conn = ConexionBD.getConnection();
            if (conn != null && !conn.isClosed()){
                System.out.println("Conexion success!!!, I'm happy");
            }else{
                System.out.println("Error conexion, I'm sad");
            }

        } catch (Exception e) {
            System.out.println("Error de conexion"+e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        testConexion();
    }
}
