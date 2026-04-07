package com.example.onlinevotingsystem;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import util.DataStore;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class VoterRegisterController {

    @FXML
    private TextField voterIdField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    @FXML
    private void handleRegister() {
        String voterId = voterIdField.getText();
        String password = passwordField.getText();

        if (voterId.isEmpty() || password.isEmpty()) {
            messageLabel.setText("⚠ Please fill all fields");
            return;
        }

        boolean success = DataStore.registerVoter(voterId, password);

        if (success) {
            messageLabel.setText("✅ Voter registered successfully");
            voterIdField.clear();
            passwordField.clear();
        } else {
            messageLabel.setText("❌ Voter ID already exists");
        }
    }

    @FXML
    private void backToLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/onlinevotingsystem/login-view.fxml")
            );

            Stage stage = new Stage();
            stage.setTitle("Online Voting System");
            stage.setScene(new Scene(loader.load(), 400, 300));
            stage.show();

            // close current window
            messageLabel.getScene().getWindow().hide();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
