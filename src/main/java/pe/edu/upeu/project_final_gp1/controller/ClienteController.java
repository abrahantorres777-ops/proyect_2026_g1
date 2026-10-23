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
import pe.edu.upeu.project_final_gp1.implementacion.ClienteDaoImpl;
import pe.edu.upeu.project_final_gp1.interfaces.IClienteDao;
import pe.edu.upeu.project_final_gp1.modelo.Cliente;
import pe.edu.upeu.project_final_gp1.utils.Alerts;

import java.net.URL;
import java.util.List;

public class ClienteController {

    private final Alerts alerts = new Alerts();

    @FXML
    private TableView<Cliente> tableCliente;

    @FXML
    private TableColumn<Cliente, Number> ColNumero;

    @FXML
    private TableColumn<Cliente, Integer> ColId;

    @FXML
    private TableColumn<Cliente, String> ColNombre;

    @FXML
    private TableColumn<Cliente, String> ColApellido;

    @FXML
    private TableColumn<Cliente, String> ColDni;

    @FXML
    private TableColumn<Cliente, String> ColEmail;

    @FXML
    private TableColumn<Cliente, String> ColGenero;

    private final IClienteDao clienteDao = new ClienteDaoImpl();

    private final ObservableList<Cliente> listaCliente =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        ColNumero.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(Number item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : String.valueOf(getIndex() + 1));
            }
        });

        ColId.setCellValueFactory(
                data -> data.getValue().idclienteProperty().asObject()
        );

        ColNombre.setCellValueFactory(
                data -> data.getValue().nombresProperty()
        );

        ColApellido.setCellValueFactory(
                data -> data.getValue().apellidosProperty()
        );

        ColDni.setCellValueFactory(
                data -> data.getValue().dniProperty()
        );

        ColEmail.setCellValueFactory(
                data -> data.getValue().emailProperty()
        );

        ColGenero.setCellValueFactory(
                data -> data.getValue().generoProperty()
        );

        cargarDatos();
    }

    private void cargarDatos() {
        try {
            List<Cliente> clientes = clienteDao.listAll();
            listaCliente.setAll(
                    clientes == null ? List.of() : clientes
            );
            tableCliente.setItems(listaCliente);
            tableCliente.refresh();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta(
                    "Error",
                    "No se pudieron cargar los clientes.",
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
        Cliente seleccionado =
                tableCliente.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrarAlerta(
                    "Selección requerida",
                    "Seleccione un cliente para editar.",
                    Alert.AlertType.WARNING
            );
            return;
        }

        Cliente clienteCompleto =
                clienteDao.searchById(seleccionado.getIdcliente());

        if (clienteCompleto == null) {
            mostrarAlerta(
                    "Error",
                    "No se encontró el cliente seleccionado.",
                    Alert.AlertType.ERROR
            );
            return;
        }

        showForm(clienteCompleto);
    }

    private void showForm(Cliente cliente) {
        try {
            String ruta =
                    "/pe/edu/upeu/project_final_gp1/views/cliente-forn.fxml";

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

            ClienteFornController controller = loader.getController();
            controller.setCliente(cliente);

            Stage stage = new Stage();
            stage.setTitle(
                    cliente == null ? "Nuevo Cliente" : "Editar Cliente"
            );
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();

            if (!controller.isSaved()) {
                return;
            }

            Cliente clienteProcesado = controller.getCliente();

            if (clienteProcesado == null) {
                mostrarAlerta(
                        "Error",
                        "No se recibieron los datos del cliente.",
                        Alert.AlertType.ERROR
                );
                return;
            }

            boolean resultado = cliente == null
                    ? clienteDao.insert(clienteProcesado)
                    : clienteDao.update(clienteProcesado);

            if (resultado) {
                mostrarAlerta(
                        "Éxito",
                        cliente == null
                                ? "Cliente registrado correctamente."
                                : "Cliente actualizado correctamente.",
                        Alert.AlertType.INFORMATION
                );
                cargarDatos();
            } else {
                mostrarAlerta(
                        "Error",
                        cliente == null
                                ? "No se pudo registrar el cliente."
                                : "No se pudo actualizar el cliente.",
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

    @FXML
    private void handleDelete() {
        Cliente seleccionado =
                tableCliente.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrarAlerta(
                    "Selección requerida",
                    "Seleccione un cliente para eliminar.",
                    Alert.AlertType.WARNING
            );
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar eliminación");
        confirmacion.setHeaderText("Eliminar cliente");
        confirmacion.setContentText(
                "¿Está seguro de eliminar a "
                        + seleccionado.getNombres() + " "
                        + seleccionado.getApellidos() + "?"
        );

        if (confirmacion.showAndWait().orElse(ButtonType.CANCEL)
                != ButtonType.OK) {
            return;
        }

        boolean eliminado = clienteDao.delete(seleccionado);

        if (eliminado) {
            mostrarAlerta(
                    "Éxito",
                    "Cliente eliminado correctamente.",
                    Alert.AlertType.INFORMATION
            );
            cargarDatos();
        } else {
            mostrarAlerta(
                    "Error",
                    "No se pudo eliminar el cliente.",
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
