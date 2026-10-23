package pe.edu.upeu.project_final_gp1.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import pe.edu.upeu.project_final_gp1.modelo.Cliente;
import pe.edu.upeu.project_final_gp1.utils.Alerts;

public class ClienteFornController {

    private static final Alerts alerts = new Alerts();

    @FXML
    private Label lblTitulo;

    @FXML
    private TextField txtNombre, txtApellidos, txtDni, txtEmail;

    @FXML
    private ComboBox<String> cbGenero;

    private Cliente cliente;

    private boolean saved = false;

    @FXML
    public void initialize() {
        cbGenero.getItems().setAll("M", "F");
    }

    public void setCliente(Cliente c) {
        this.cliente = c;

        if (c != null) {
            lblTitulo.setText("Editar Cliente");

            txtNombre.setText(c.getNombres());
            txtApellidos.setText(c.getApellidos());
            txtDni.setText(c.getDni());
            txtEmail.setText(c.getEmail());
            cbGenero.setValue(c.getGenero());
        } else {
            lblTitulo.setText("Nuevo Cliente");
            saved = false;
        }
    }

    public boolean isSaved() {
        return saved;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void cerrar() {
        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.close();
    }

    private boolean validated() {
        if (txtNombre.getText() == null
                || txtNombre.getText().trim().isEmpty()
                || txtDni.getText() == null
                || txtDni.getText().trim().isEmpty()) {

            Alert alert = alerts.viewAlers(
                    "Error",
                    "Nombre y DNI son obligatorios.",
                    "WARNING"
            );

            alert.showAndWait();
            return false;
        }

        return true;
    }

    @FXML
    private void handleCancel() {
        cerrar();
    }

    @FXML
    private void handleSaved() {
        if (!validated()) {
            return;
        }

        if (cliente == null) {
            cliente = new Cliente();
        }

        cliente.setNombres(txtNombre.getText().trim());
        cliente.setApellidos(txtApellidos.getText().trim());
        cliente.setDni(txtDni.getText().trim());
        cliente.setEmail(txtEmail.getText().trim());
        cliente.setGenero(cbGenero.getValue());

        saved = true;
        cerrar();
    }
}
