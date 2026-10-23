package pe.edu.upeu.project_final_gp1.modelo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Persona {
    private final StringProperty nombres = new SimpleStringProperty();
    private final StringProperty apellidos = new SimpleStringProperty();
    private final StringProperty dni = new SimpleStringProperty();

    public Persona() {
    }
    public Persona(String nombres, String apellidos, String dni) {
        setNombres(nombres);
        setApellidos(apellidos);
        setDni(dni);
    }


    public final String getDni() {
        return dni.get();
    }

    public final void setDni(String value) {
        dni.set(value);
    }

    public final StringProperty dniProperty() {
        return dni;
    }

    public final String getApellidos() {
        return apellidos.get();
    }

    public final void setApellidos(String value) {
        apellidos.set(value);
    }

    public final StringProperty apellidosProperty() {
        return apellidos;
    }

    public final String getNombres() {
        return nombres.get();
    }

    public final void setNombres(String value) {
        nombres.set(value);
    }

    public final StringProperty nombresProperty() {
        return nombres;
    }

}
