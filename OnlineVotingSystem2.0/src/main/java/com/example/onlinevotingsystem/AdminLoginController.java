package com.example.onlinevotingsystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminLoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    @FXML
    private void handleLogin() {
        String user = usernameField.getText();
        String pass = passwordField.getText();

        if (user.equals("admin") && pass.equals("admin")) {
            openAdminDashboard();
//            ((Stage) usernameField.getScene().getWindow()).close();
        } else {
            messageLabel.setText("Invalid admin credentials");
        }
    }

    private void openAdminDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/onlinevotingsystem/admin-view.fxml")
            );
//            Stage stage = new Stage();
//            stage.setTitle("Admin Dashboard");
//            stage.setScene(new Scene(loader.load(), 400, 350));
//            stage.show();

            Parent root = loader.load();

            // ✅ GET CURRENT STAGE
            Stage stage = (Stage) usernameField
                    .getScene()
                    .getWindow();

            // ✅ STEP 2: REPLACE ROOT (NOT SCENE)
            stage.getScene().setRoot(root);
            stage.sizeToScene();
            stage.centerOnScreen();
        } catch (Exception e) {
            e.printStackTrace(); // DO NOT REMOVE
        }
    }
}
