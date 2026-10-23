package pe.edu.upeu.project_final_gp1.test;

import pe.edu.upeu.project_final_gp1.implementacion.HabitacionDaoImpl;
import pe.edu.upeu.project_final_gp1.interfaces.IHabitacionDao;
import pe.edu.upeu.project_final_gp1.modelo.Habitacion;

import java.util.List;

public class TestHabitacion {

    static IHabitacionDao hDao = new HabitacionDaoImpl();

    public void insertar() {
        Habitacion h = new Habitacion();

        h.setNumero("100");
        h.setNumeroPiso("1");
        h.setNumeroHabitacion("101");
        h.setTipo("SIMPLE");
        h.setPrecio(150.0);
        h.setEstado("Disponible");

        boolean result = hDao.insert(h);

        if (result) {
            System.out.println("Success Insert");
        } else {
            System.out.println("ERROR INSERT");
        }
    }

    public void delete() {
        Habitacion h = new Habitacion();
        h.setIdhabitacion(1);

        boolean result = hDao.delete(h);

        if (result) {
            System.out.println("Success Delete");
        } else {
            System.out.println("ERROR DELETE");
        }
    }

    public void listarHabitacion() {
        List<Habitacion> lista = hDao.listAll();

        if (lista != null && !lista.isEmpty()) {
            for (Habitacion h : lista) {
                System.out.println("ID_HABITACION: " + h.getIdhabitacion());
                System.out.println("NUMERO: " + h.getNumero());
                System.out.println("PISO: " + h.getNumeroPiso());
                System.out.println("NUMERO HABITACION: " + h.getNumeroHabitacion());
                System.out.println("TIPO: " + h.getTipo());
                System.out.println("PRECIO: " + h.getPrecio());
                System.out.println("ESTADO: " + h.getEstado());
                System.out.println("------------------------------");
            }
        } else {
            System.out.println("No hay registros");
        }
    }

    public void searchById() {
        Habitacion h = hDao.searchById(1);

        if (h != null) {
            System.out.println("ID_HABITACION: " + h.getIdhabitacion());
            System.out.println("NUMERO: " + h.getNumero());
            System.out.println("PISO: " + h.getNumeroPiso());
            System.out.println("NUMERO HABITACION: " + h.getNumeroHabitacion());
            System.out.println("TIPO: " + h.getTipo());
            System.out.println("PRECIO: " + h.getPrecio());
            System.out.println("ESTADO: " + h.getEstado());
        } else {
            System.out.println("No hay registros");
        }
    }

    public void searchByNumero() {
        Habitacion h = hDao.searchByNumero("100");

        if (h != null) {
            System.out.println("ID_HABITACION: " + h.getIdhabitacion());
            System.out.println("NUMERO: " + h.getNumero());
            System.out.println("PISO: " + h.getNumeroPiso());
            System.out.println("NUMERO HABITACION: " + h.getNumeroHabitacion());
            System.out.println("TIPO: " + h.getTipo());
            System.out.println("PRECIO: " + h.getPrecio());
            System.out.println("ESTADO: " + h.getEstado());
        } else {
            System.out.println("No hay registros");
        }
    }

    public void Update() {
        Habitacion h = new Habitacion();

        h.setIdhabitacion(1);
        h.setNumero("100");
        h.setNumeroPiso("1");
        h.setNumeroHabitacion("101");
        h.setTipo("DOBLE");
        h.setPrecio(150.0);
        h.setEstado("Disponible");

        boolean result = hDao.update(h);

        if (result) {
            System.out.println("Success Update");
        } else {
            System.out.println("ERROR UPDATE");
        }
    }

    public static void main(String[] args) {
        TestHabitacion t = new TestHabitacion();

        System.out.println("********** TEST HABITACION **********");

        t.listarHabitacion();
        // t.insertar();
        // t.searchById();
        // t.searchByNumero();
        // t.Update();
        // t.delete();
    }
}
