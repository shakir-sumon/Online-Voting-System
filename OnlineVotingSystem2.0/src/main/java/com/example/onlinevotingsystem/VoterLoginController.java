package com.example.onlinevotingsystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Voter;
import util.DataStore;

public class VoterLoginController {

    @FXML
    private TextField voterIdField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;

    @FXML
    private void handleLogin() {
        String voterId = voterIdField.getText();
        String password = passwordField.getText();

        if (voterId.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Please enter Voter ID and Password");
            return;
        }

        Voter voter = DataStore.authenticateVoter(voterId, password);

        if (voter == null) {
            messageLabel.setText("Invalid Voter ID or Password");
            return;
        }

        openVotingScreen(voter);
        voterIdField.getScene().getWindow().hide();
    }

    private void openVotingScreen(Voter voter) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/onlinevotingsystem/voter-view.fxml")
            );

            Scene scene = new Scene(loader.load(), 400, 350);

            VoterController controller = loader.getController();
            controller.setLoggedInVoter(voter);

            Stage stage = new Stage();
            stage.setTitle("Vote Panel");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
