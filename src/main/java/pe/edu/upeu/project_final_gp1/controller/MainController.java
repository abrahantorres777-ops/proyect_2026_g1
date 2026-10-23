package pe.edu.upeu.project_final_gp1.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.Stack;

public class MainController {

    @FXML
    private StackPane contentArea;

    @FXML
    public void initialize() {
        showHome();
    }

    @FXML
    private void showHome() {
        loadView("home-view.fxml");
    }

    @FXML
    private void showCliente() {
        loadView("cliente-view.fxml");
    }

    @FXML
    private void showHabitacion() {
        loadView("habitacion-view.fxml");
    }

    private void loadView(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/pe/edu/upeu/project_final_gp1/views/" + fxml
                    )
            );

            Parent view = loader.load();
            contentArea.getChildren().setAll(view);

        } catch (IOException e) {
            System.out.println("ERROR DE CARGA DE LA VISTA: " + fxml);
            e.printStackTrace();
        }
    }
}