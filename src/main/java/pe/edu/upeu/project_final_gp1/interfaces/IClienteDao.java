package pe.edu.upeu.project_final_gp1.interfaces;

import pe.edu.upeu.project_final_gp1.modelo.Cliente;

import java.util.List;

public interface IClienteDao {
    public boolean insert(Cliente c);
    public boolean update(Cliente c);
    public boolean delete(Cliente c);
    public Cliente searchById(int id);
    public Cliente searchByDni(String dni);
    public List<Cliente> listAll();
}
