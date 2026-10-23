package pe.edu.upeu.project_final_gp1.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pe.edu.upeu.project_final_gp1.modelo.Habitacion;
import pe.edu.upeu.project_final_gp1.utils.Alerts;

public class HabitacionFornController {
    private static Alerts alerts = new Alerts();

    @FXML
    private Label lblTitulo;

    @FXML
    private TextField txtNumero, txtNumeroPiso, txtNumeroHabitacion, txtPrecio;

    @FXML
    private ComboBox<String> cbTipo, cbEstado;

    private Habitacion habitacion;

    private boolean saved = false;

    @FXML
    public void initialize() {
        cbTipo.getItems().addAll(
                "SIMPLE",
                "DOBLE",
                "MATRIMONIAL",
                "SUITE"
        );

        cbEstado.getItems().addAll(
                "Disponible",
                "Ocupada",
                "Mantenimiento"
        );
    }


    public void setHabitacion(Habitacion h) {
        this.habitacion = h;

        if (h != null) {
            lblTitulo.setText("Editar Habitación");
            txtNumero.setText(h.getNumero());
            txtNumeroPiso.setText(h.getNumeroPiso());
            txtNumeroHabitacion.setText(h.getNumeroHabitacion());
            txtPrecio.setText(String.valueOf(h.getPrecio()));
            cbTipo.setValue(h.getTipo());
            cbEstado.setValue(h.getEstado());
        }
    }

    public boolean isSaved() {
        return saved;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void cerrar() {
        Stage stage = (Stage) txtNumero.getScene().getWindow();
        stage.close();
    }

    private boolean validated() {
        if (txtNumero.getText().isEmpty()
                && txtNumeroPiso.getText().isEmpty()
                && txtNumeroHabitacion.getText().isEmpty()
                && txtPrecio.getText().isEmpty()) {

            Alert alert = alerts.viewAlers(
                    "ERROR",
                    "Los datos de la habitación son obligatorios",
                    "WARNING"
            );
            alert.show();
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
        if (validated()) {
            if (habitacion == null) {
                habitacion = new Habitacion();
            }

            habitacion.setNumero(txtNumero.getText());
            habitacion.setNumeroPiso(txtNumeroPiso.getText());
            habitacion.setNumeroHabitacion(txtNumeroHabitacion.getText());
            habitacion.setTipo(cbTipo.getValue());
            habitacion.setPrecio(Double.parseDouble(txtPrecio.getText()));
            habitacion.setEstado(cbEstado.getValue());

            saved = true;
            cerrar();
        }
    }
}

