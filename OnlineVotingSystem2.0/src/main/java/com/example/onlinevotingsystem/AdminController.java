package com.example.onlinevotingsystem;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.Candidate;
import util.DataStore;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminController {

    @FXML
    private TextField candidateNameField;

    @FXML
    private TextArea outputArea;

    @FXML
    private void handleAddCandidate() {
        String name = candidateNameField.getText();

        if (name.isEmpty()) {
            outputArea.setText("Candidate name cannot be empty!");
            return;
        }
        DataStore.addCandidate(name);
        outputArea.setText("Candidate added: " + name);
        candidateNameField.clear();
    }
    @FXML
    private void openVoterRegistration() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/onlinevotingsystem/voter-register-view.fxml")
            );

            Stage stage = new Stage();
            stage.setTitle("Voter Registration");
            stage.setScene(new Scene(loader.load(), 350, 250));

            stage.setOnCloseRequest(e -> {
                DataStore.saveData();
                System.exit(0);
            });
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleViewResult() {
        StringBuilder result = new StringBuilder("Candidate List:\n");

        for (Candidate c : DataStore.getCandidates()) {
            result.append(c.getId())
                    .append(". ")
                    .append(c.getName())
                    .append(" - ")
                    .append(c.getVotes())
                    .append(" votes\n");
        }
        outputArea.setText(result.toString());
    }
}
