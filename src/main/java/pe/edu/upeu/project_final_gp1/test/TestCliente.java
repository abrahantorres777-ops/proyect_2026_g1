package pe.edu.upeu.project_final_gp1.test;

import pe.edu.upeu.project_final_gp1.implementacion.ClienteDaoImpl;
import pe.edu.upeu.project_final_gp1.implementacion.HabitacionDaoImpl;
import pe.edu.upeu.project_final_gp1.interfaces.IClienteDao;
import pe.edu.upeu.project_final_gp1.interfaces.IHabitacionDao;
import pe.edu.upeu.project_final_gp1.modelo.Cliente;
import pe.edu.upeu.project_final_gp1.modelo.Habitacion;

import java.util.List;

public class TestCliente {
    static IClienteDao cl = new ClienteDaoImpl();

    public void insertar(){
        Cliente c = new Cliente();
        c.setNombres("Juan");
        c.setApellidos("Perez");
        c.setDni("0123456");
        c.setEmail("juan@gmail.com");
        c.setGenero("M");
        boolean result = cl.insert(c);
        if (result){
            System.out.println("Success");
        }else {
            System.out.println("Error");
        }
    }
    public void delete(){
        Cliente c = new Cliente();
        c.setIdcliente(1);
        boolean result = cl.insert(c);
        if (result){
            System.out.println("Success delete");
        }else {
            System.out.println("Error delete");
        }
    }
    public void update(){
        Cliente c = new Cliente();
        c.setNombres("Jarly");
        c.setApellidos("Sanchez");
        c.setDni("0123456");
        c.setEmail("jarly@gmail.com");
        c.setGenero("M");
        c.setIdcliente(1);
        boolean result = cl.update(c);
        if (result){
            System.out.println("Success update");
        }else {
            System.out.println("Error");
        }
    }

    public void listarCliente(){
        List<Cliente> lista = cl.listAll();
        if (lista !=null && !lista.isEmpty()){
            for (Cliente c : lista){
                System.out.println("ID_CLIENTE:"+c.getIdcliente());
                System.out.println("NOMBRES:"+c.getNombres());
                System.out.println("APELLIDOS:"+c.getApellidos());
                System.out.println("DNI:"+c.getDni());
                System.out.println("EMAIL:"+c.getEmail());
                System.out.println("GENERO:"+c.getGenero());
            }
        }else{
            System.out.println("No hay registros");
        }
    }
    public void searchById(){
        Cliente c = cl.searchById(1);
        if (c != null){
            System.out.println("ID_CLIENTE:"+c.getIdcliente());
            System.out.println("NOMBRES:"+c.getNombres());
            System.out.println("APELLIDOS:"+c.getApellidos());
            System.out.println("DNI:"+c.getDni());
            System.out.println("EMAIL:"+c.getEmail());
            System.out.println("GENERO:"+c.getGenero());
        }else{
            System.out.println("No hay registros");
        }
    }
    public void searchByDni() {
        Cliente c = cl.searchByDni("61035877");
        if (c != null) {
            System.out.println("ID_CLIENTE:" + c.getIdcliente());
            System.out.println("NOMBRES:" + c.getNombres());
            System.out.println("APELLIDOS:" + c.getApellidos());
            System.out.println("DNI:" + c.getDni());
            System.out.println("EMAIL:" + c.getEmail());
            System.out.println("GENERO:" + c.getGenero());
        } else {
            System.out.println("No hay registros");
        }
    }

    public static void main(String[] args) {
        TestCliente t = new TestCliente();
        System.out.println("************");
        t.listarCliente();
        //t.searchByDni();
        //t.searchById();
        //t.update();
    }
}