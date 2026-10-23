package pe.edu.upeu.project_final_gp1.implementacion;

import pe.edu.upeu.project_final_gp1.interfaces.IHabitacionDao;
import pe.edu.upeu.project_final_gp1.modelo.Habitacion;
import pe.edu.upeu.project_final_gp1.utils.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HabitacionDaoImpl implements IHabitacionDao {

    @Override
    public boolean insert(Habitacion h) {
        boolean flag = false;
        String query = "INSERT INTO habitacion(numero,numeroPiso,numeroHabitacion,tipo,precio,estado)" +
                "VALUES(?,?,?,?,?,?);";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement st = cn.prepareStatement(query)) {
            st.setString(1,h.getNumero());
            st.setString(2,h.getNumeroPiso());
            st.setString(3,h.getNumeroHabitacion());
            st.setString(4,h.getTipo());
            st.setDouble(5,h.getPrecio());
            st.setString(6,h.getEstado());
            st.executeUpdate();
            flag=true;
        } catch (SQLException e) {
            System.out.println("Error al insertar habitacion:" + e.getMessage());
        }
        return flag;
    }


    @Override
    public boolean update(Habitacion h) {

        String query = "UPDATE habitacion SET "
                + "numero = ?, "
                + "numeroPiso = ?, "
                + "numeroHabitacion = ?, "
                + "tipo = ?, "
                + "precio = ?, "
                + "estado = ? "
                + "WHERE idhabitacion = ?";

        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement st = cn.prepareStatement(query)) {

            st.setString(1, h.getNumero());
            st.setString(2, h.getNumeroPiso());
            st.setString(3, h.getNumeroHabitacion());
            st.setString(4, h.getTipo());
            st.setDouble(5, h.getPrecio());
            st.setString(6, h.getEstado());
            st.setInt(7, h.getIdhabitacion());

            int filasActualizadas = st.executeUpdate();

            return filasActualizadas > 0;

        } catch (SQLException e) {
            System.err.println(
                    "Error al actualizar habitación: " + e.getMessage()
            );
            e.printStackTrace();
            return false;
        }
    }



    @Override
    public Habitacion searchById(int id) {

        Habitacion h = null;
        String query = "SELECT * FROM habitacion WHERE idhabitacion = ?";

        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement st = cn.prepareStatement(query)) {

            st.setInt(1, id);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    h = new Habitacion();

                    h.setIdhabitacion(rs.getInt("idhabitacion"));
                    h.setNumero(rs.getString("numero"));
                    h.setNumeroPiso(rs.getString("numeroPiso"));
                    h.setNumeroHabitacion(rs.getString("numeroHabitacion"));
                    h.setTipo(rs.getString("tipo"));
                    h.setPrecio(rs.getDouble("precio"));
                    h.setEstado(rs.getString("estado"));
                }
            }

        } catch (SQLException e) {
            System.err.println(
                    "Error al buscar habitación por ID: " + e.getMessage()
            );
            e.printStackTrace();
        }

        return h;
    }


    @Override
    public boolean delete(Habitacion h) {
        String query = "DELETE FROM habitacion WHERE idhabitacion = ?";

        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement st = cn.prepareStatement(query)) {

            st.setInt(1, h.getIdhabitacion());

            return st.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar habitación: " + e.getMessage());
            return false;
        }

    }

    @Override
    public List<Habitacion> listAll() {
        List<Habitacion> lista = new ArrayList<>();
        String query = "SELECT * FROM habitacion;";
        try (
                Connection cn = ConexionBD.getConnection();
                PreparedStatement st = cn.prepareStatement(query);
                ResultSet rs = st.executeQuery();
        ) {
            while (rs.next()){
                Habitacion h = new Habitacion();
                h.setIdhabitacion(rs.getInt("idhabitacion"));
                h.setNumero(rs.getString("numero"));
                h.setNumeroPiso(rs.getString("numeroPiso"));
                h.setNumeroHabitacion(rs.getString("numeroHabitacion"));
                h.setTipo(rs.getString("tipo"));
                h.setPrecio(rs.getDouble("precio"));
                h.setEstado(rs.getString("estado"));
                lista.add(h);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar habitacion:" + e.getMessage());
        }
        return lista;
    }

    @Override
    public Habitacion searchByNumero(String numero) {
        Habitacion h = null;
        String query = "SELECT * FROM habitacion WHERE numero=?;";
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement st = cn.prepareStatement(query)) {
            st.setString(1,numero);
            try( ResultSet rs = st.executeQuery()) {
                if (rs.next()){
                    h = new Habitacion();
                    h.setIdhabitacion(rs.getInt("idhabitacion"));
                    h.setNumero(rs.getString("numero"));
                    h.setNumeroPiso(rs.getString("numeroPiso"));
                    h.setNumeroHabitacion(rs.getString("numeroHabitacion"));
                    h.setTipo(rs.getString("tipo"));
                    h.setPrecio(rs.getDouble("precio"));
                    h.setEstado(rs.getString("estado"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al listar habitacion por numero:" + e.getMessage());
        }
        return h;
    }
}
