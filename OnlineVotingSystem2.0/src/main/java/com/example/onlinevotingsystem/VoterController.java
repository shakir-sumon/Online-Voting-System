package com.example.onlinevotingsystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Candidate;
import model.Voter;
import util.DataStore;
import javafx.animation.PauseTransition;
import javafx.util.Duration;


public class VoterController {

    @FXML
    private ComboBox<Candidate> candidateComboBox;

    @FXML
    private Label messageLabel;

    private Voter loggedInVoter;

    // called from VoterLoginController
    public void setLoggedInVoter(Voter voter) {
        this.loggedInVoter = voter;
    }

    @FXML
    public void initialize() {
        candidateComboBox.getItems().addAll(DataStore.getCandidates());

        // show candidate name instead of object ref
        candidateComboBox.setCellFactory(cb -> new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(Candidate c, boolean empty) {
                super.updateItem(c, empty);
                setText(empty || c == null ? null : c.getId() + ". " + c.getName());
            }
        });

        candidateComboBox.setButtonCell(candidateComboBox.getCellFactory().call(null));
    }

    @FXML
    private void handleVote() {
        if (loggedInVoter == null) {
            messageLabel.setText("Session expired. Please login again.");
            return;
        }
        Candidate selected = candidateComboBox.getValue();
        if (selected == null) {
            messageLabel.setText("Please select a candidate");
            return;
        }
        boolean success = DataStore.castVote(
                selected.getId(),
                loggedInVoter.getVoterID(),
                loggedInVoter.getPassword()
        );
        if (success) {
            messageLabel.setText("✅ Vote cast successfully!");
            new Thread(() -> {
                try {
                    Thread.sleep(1200);
                    javafx.application.Platform.runLater(this::goBackToMain);
                } catch (Exception ignored) {}
            }).start();
        } else {
            messageLabel.setText("❌ You already voted");
            // auto return to main page after short delay
            PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
            pause.setOnFinished(e -> goBackToMain());
            pause.play();
        }

    }

    private void goBackToMain() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/onlinevotingsystem/login-view.fxml")
            );

            Stage stage = new Stage();
            stage.setTitle("Online Voting System");
            stage.setScene(new Scene(loader.load(), 400, 300));
            stage.show();

            // close vote panel
            ((Stage) messageLabel.getScene().getWindow()).close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
