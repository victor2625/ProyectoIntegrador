package com.serranito.view;

import com.serranito.controller.LoginController;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginView {
    private final LoginController controller = new LoginController();

    public void start(Stage stage) {
        TextField userField = new TextField();
        PasswordField passField = new PasswordField();
        Button loginBtn = new Button("Iniciar sesión");
        Label status = new Label();

        loginBtn.setOnAction(e -> {
            boolean ok = controller.autenticar(userField.getText(), passField.getText());
            if (ok) {
                status.setText("Ingreso exitoso");
                InventarioView inventarioView = new InventarioView();
                inventarioView.start(new Stage());
                stage.close();
            } else {
                status.setText("Credenciales incorrectas");
            }
        });

        VBox root = new VBox(10,
                new Label("Usuario:"), userField,
                new Label("Contraseña:"), passField,
                loginBtn, status);
        Scene scene = new Scene(root, 300, 200);
        stage.setScene(scene);
        stage.setTitle("Login - El Serranito del Norte");
        stage.show();
    }
}