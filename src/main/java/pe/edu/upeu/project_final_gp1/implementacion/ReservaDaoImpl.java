package pe.edu.upeu.project_final_gp1.implementacion;

import pe.edu.upeu.project_final_gp1.interfaces.IReservaDao;
import pe.edu.upeu.project_final_gp1.modelo.Cliente;
import pe.edu.upeu.project_final_gp1.modelo.Habitacion;
import pe.edu.upeu.project_final_gp1.modelo.Reserva;
import pe.edu.upeu.project_final_gp1.modelo.Reserva;
import pe.edu.upeu.project_final_gp1.utils.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservaDaoImpl implements IReservaDao {

    @Override
    public boolean insert(Reserva r) {
        boolean flag = false;
        String query = " INSERT INTO reserva(clienteId, habitacionId, fecha_reserva,fecha_ingreso,   " +
                " fecha_salida,total,estado) VALUES(?,?,?,?,?,?,?) ";
        try (Connection cn = ConexionBD.getConnection()) {
            PreparedStatement st = cn.prepareStatement(query);

            st.setInt(1, r.getCliente().getIdcliente());
            st.setInt(2, r.getHabitacion().getIdhabitacion());
            st.setString(3, r.getFechaReserva().toString());
            st.setString(4, r.getFechaIngreso().toString());
            st.setString(5, r.getFechaSalida().toString());
            st.setDouble(6, r.getTotal());
            st.setString(7, r.getEstado());
            st.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            System.out.println("error de insert" + e.getMessage());
        }
        return flag;
    }

    @Override
    public boolean update(Reserva r) {
        return false;
    }

    @Override
    public boolean delete(Reserva r) {
        return false;
    }

    @Override
    public Reserva searchById(int id) {
        return null;
    }

    @Override
    public List<Reserva> listAll() {
        List<Reserva> lista = new ArrayList<>();
        String query = " SELECT r.*,c.nombres ||' ' || c.apellidos AS nombre_completo, c.dni, " +
                " h.numero, h.numeroPiso, h.numeroHabitacion, h.precio, h.estado AS estado_hab " +
                " FROM reserva r, cliente c, habitacion h " +
                " WHERE r.clienteId = c.idcliente " +
                " AND r.habitacionId = h.idhabitacion ";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement st = conn.prepareStatement(query);
             ResultSet rs = st.executeQuery()) {
            System.out.println("rs" + rs);
            while (rs.next()) {
                System.out.println("entra al while");
                Cliente cl = new Cliente();
                Habitacion h = new Habitacion();
                Reserva r = new Reserva();
                cl.setIdcliente(rs.getInt("clienteid"));
                cl.setNombres(rs.getString("nombre_completo"));
                cl.setDni(rs.getString("dni"));

                h.setIdhabitacion(rs.getInt("habitacionid"));
                h.setNumero(rs.getString("numero"));
                h.setNumeroPiso(rs.getString("numeroPiso"));
                h.setNumeroHabitacion(rs.getString("numeroHabitacion"));
                h.setPrecio(rs.getDouble("precio"));

                r.setIdreserva(rs.getInt("idreserva"));
                r.setFechaReserva(LocalDate.parse(rs.getString("fecha_reserva")));
                r.setFechaIngreso(LocalDate.parse(rs.getString("fecha_ingreso")));
                r.setFechaSalida(LocalDate.parse(rs.getString("fecha_salida")));
                r.setTotal(rs.getDouble("total"));
                r.setEstado(rs.getString("estado"));

                r.setCliente(cl);
                r.setHabitacion(h);
                lista.add(r);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar reserva" + e.getMessage());
        }

        return lista;
    }


    @Override
    public boolean updateStatus(Reserva r) {
        String query = "UPDATE reserva SET estado = ? WHERE idreserva=? ";
        boolean flag = false;
        try (Connection cn = ConexionBD.getConnection();
             PreparedStatement st = cn.prepareStatement(query)) {

            st.setString(1, r.getEstado());
            st.setInt(2, r.getIdreserva());
            st.executeUpdate();
            flag = true;

        } catch (SQLException e) {
            System.err.println("Error al actualizar reserva:");
            e.getMessage();

        }
        return flag;
    }


@Override
public boolean validateRoom(Reserva r) {
    String query = " SELECT count(1) FROM reserva " +
            "WHERE habitacionId = ? " +
            " AND estado IN ('R','O') " +
            " AND (? < fecha_salida " +
            " AND ? > fecha_ingreso) ";
    boolean ocupado = false;
    try (Connection cn = ConexionBD.getConnection();
         PreparedStatement st = cn.prepareStatement(query)) {

        st.setInt(1, r.getHabitacion().getIdhabitacion());
        st.setString(2, r.getFechaIngreso().toString());
        st.setString(2, r.getFechaSalida().toString());
        try (ResultSet rs = st.executeQuery()){
            if (rs.next()){
                ocupado = rs.getInt(1)>0;
            }
        }
        ocupado = true;
    } catch (SQLException e) {
        System.err.println("Error al validar habitacion:");
        e.getMessage();

    }
    return ocupado;
}
}
