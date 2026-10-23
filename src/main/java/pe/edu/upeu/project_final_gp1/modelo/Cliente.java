package pe.edu.upeu.project_final_gp1.modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Cliente extends Persona {
    private final IntegerProperty idcliente = new SimpleIntegerProperty();
    private final StringProperty email = new SimpleStringProperty();
    private final StringProperty genero = new SimpleStringProperty();

    public Cliente() {
    }

    public Cliente(String nombres, String apellidos, String dni, int idcliente, String email, String genero) {
        super(nombres, apellidos, dni);
        setIdcliente(idcliente);
        setEmail(email);
        setGenero(genero);
    }

    public final String getGenero() {
        return genero.get();
    }

    public final void setGenero(String value) {
        genero.set(value);
    }

    public final StringProperty generoProperty() {
        return genero;
    }

    public final String getEmail() {
        return email.get();
    }

    public final void setEmail(String value) {
        email.set(value);
    }

    public final StringProperty emailProperty() {
        return email;
    }

    public final int getIdcliente() {
        return idcliente.get();
    }

    public final void setIdcliente(int value) {
        idcliente.set(value);
    }

    public final IntegerProperty idclienteProperty() {
        return idcliente;
    }

}
