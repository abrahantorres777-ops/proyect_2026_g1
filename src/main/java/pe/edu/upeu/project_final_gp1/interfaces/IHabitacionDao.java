package pe.edu.upeu.project_final_gp1.interfaces;

import pe.edu.upeu.project_final_gp1.modelo.Habitacion;

import java.util.List;

public interface IHabitacionDao {
    public boolean insert(Habitacion h);

    public boolean update(Habitacion h);

    public Habitacion searchById(int id);

    public boolean delete(Habitacion h);

    public List<Habitacion> listAll();

    public Habitacion searchByNumero(String numero);
}
