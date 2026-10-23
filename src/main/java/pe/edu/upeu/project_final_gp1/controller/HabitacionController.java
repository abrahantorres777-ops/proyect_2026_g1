package pe.edu.upeu.project_final_gp1.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pe.edu.upeu.project_final_gp1.HelloApplication;
import pe.edu.upeu.project_final_gp1.implementacion.HabitacionDaoImpl;
import pe.edu.upeu.project_final_gp1.interfaces.IHabitacionDao;
import pe.edu.upeu.project_final_gp1.modelo.Habitacion;
import pe.edu.upeu.project_final_gp1.utils.Alerts;

import java.net.URL;
import java.util.List;

public class HabitacionController {

    private final Alerts alerts = new Alerts();

    @FXML
    private TableView<Habitacion> tableHabitacion;

    @FXML
    private TableColumn<Habitacion, Number> ColNum;

    @FXML
    private TableColumn<Habitacion, Integer> ColId;

    @FXML
    private TableColumn<Habitacion, String> ColNumero;

    @FXML
    private TableColumn<Habitacion, String> ColNumeroPiso;

    @FXML
    private TableColumn<Habitacion, String> ColNumeroHab;

    @FXML
    private TableColumn<Habitacion, String> ColTipo;

    @FXML
    private TableColumn<Habitacion, Double> Colprecio;

    @FXML
    private TableColumn<Habitacion, String> ColEstado;

    private final IHabitacionDao habitacionDao = new HabitacionDaoImpl();
    private final ObservableList<Habitacion> listaHabitaciones =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        ColNum.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Number item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : String.valueOf(getIndex() + 1));
            }
        });

        ColId.setCellValueFactory(
                data -> data.getValue().idhabitacionProperty().asObject()
        );

        ColNumero.setCellValueFactory(
                data -> data.getValue().numeroProperty()
        );

        ColNumeroPiso.setCellValueFactory(
                data -> data.getValue().numeroPisoProperty()
        );

        ColNumeroHab.setCellValueFactory(
                data -> data.getValue().numeroHabitacionProperty()
        );

        ColTipo.setCellValueFactory(
                data -> data.getValue().tipoProperty()
        );

        Colprecio.setCellValueFactory(
                data -> data.getValue().precioProperty().asObject()
        );

        ColEstado.setCellValueFactory(
                data -> data.getValue().estadoProperty()
        );

        cargarDatos();
    }

    private void cargarDatos() {
        try {
            List<Habitacion> habitaciones = habitacionDao.listAll();
            listaHabitaciones.setAll(
                    habitaciones == null ? List.of() : habitaciones
            );
            tableHabitacion.setItems(listaHabitaciones);
            tableHabitacion.refresh();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta(
                    "Error",
                    "No se pudieron cargar las habitaciones.",
                    Alert.AlertType.ERROR
            );
        }
    }

    @FXML
    private void handleNew() {
        showForm(null);
    }

    @FXML
    private void handleEdit() {
        Habitacion seleccionada =
                tableHabitacion.getSelectionModel().getSelectedItem();

        if (seleccionada == null) {
            mostrarAlerta(
                    "Advertencia",
                    "Seleccione una habitación para editar.",
                    Alert.AlertType.WARNING
            );
            return;
        }

        showForm(seleccionada);
    }

    @FXML
    private void handleDelete() {
        Habitacion seleccionada =
                tableHabitacion.getSelectionModel().getSelectedItem();

        if (seleccionada == null) {
            mostrarAlerta(
                    "Advertencia",
                    "Seleccione una habitación para eliminar.",
                    Alert.AlertType.WARNING
            );
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar eliminación");
        confirmacion.setHeaderText("Eliminar habitación");
        confirmacion.setContentText(
                "¿Está seguro de eliminar la habitación "
                        + seleccionada.getNumeroHabitacion() + "?"
        );

        if (confirmacion.showAndWait().orElse(ButtonType.CANCEL)
                != ButtonType.OK) {
            return;
        }

        boolean resultado = habitacionDao.delete(seleccionada);

        if (resultado) {
            mostrarAlerta(
                    "Éxito",
                    "Habitación eliminada correctamente.",
                    Alert.AlertType.INFORMATION
            );
            cargarDatos();
        } else {
            mostrarAlerta(
                    "Error",
                    "No se pudo eliminar la habitación.",
                    Alert.AlertType.ERROR
            );
        }
    }

    private void showForm(Habitacion habitacion) {
        try {
            String ruta =
                    "/pe/edu/upeu/project_final_gp1/views/habitacion-forn.fxml";

            URL recurso = HelloApplication.class.getResource(ruta);

            if (recurso == null) {
                mostrarAlerta(
                        "Error",
                        "No se encontró el formulario: " + ruta,
                        Alert.AlertType.ERROR
                );
                return;
            }

            FXMLLoader loader = new FXMLLoader(recurso);
            Parent root = loader.load();

            HabitacionFornController controller = loader.getController();
            controller.setHabitacion(habitacion);

            Stage stage = new Stage();
            stage.setTitle(
                    habitacion == null
                            ? "Nueva habitación"
                            : "Editar habitación"
            );
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();

            if (!controller.isSaved()) {
                return;
            }

            Habitacion procesada = controller.getHabitacion();

            if (procesada == null) {
                mostrarAlerta(
                        "Error",
                        "No se recibieron los datos de la habitación.",
                        Alert.AlertType.ERROR
                );
                return;
            }

            boolean resultado = habitacion == null
                    ? habitacionDao.insert(procesada)
                    : habitacionDao.update(procesada);

            if (resultado) {
                mostrarAlerta(
                        "Éxito",
                        habitacion == null
                                ? "Habitación registrada correctamente."
                                : "Habitación actualizada correctamente.",
                        Alert.AlertType.INFORMATION
                );
                cargarDatos();
            } else {
                mostrarAlerta(
                        "Error",
                        habitacion == null
                                ? "No se pudo registrar la habitación."
                                : "No se pudo actualizar la habitación.",
                        Alert.AlertType.ERROR
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta(
                    "Error",
                    "No se pudo abrir o procesar el formulario: "
                            + e.getMessage(),
                    Alert.AlertType.ERROR
            );
        }
    }

    private void mostrarAlerta(
            String titulo,
            String mensaje,
            Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}