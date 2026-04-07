package com.example.onlinevotingsystem;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Node;


public class LoginController {

    @FXML
    private void openAdminLogin(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/onlinevotingsystem/admin-login-view.fxml")
            );

            Parent root = loader.load();

            // get current stage
            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene().getWindow();

            // 🔥 IMPORTANT FIX
            stage.getScene().setRoot(root);

            // optional (nice UI)
            stage.centerOnScreen();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




//    @FXML
//    private void openAdminLogin(ActionEvent event) {
//        try {
//            FXMLLoader loader = new FXMLLoader(
//                    getClass().getResource("/com/example/onlinevotingsystem/admin-login-view.fxml")
//            );
//
//            Parent root = loader.load();
//
//            Stage stage = (Stage) ((Node) event.getSource())
//                    .getScene().getWindow();
//
//            // 🔥 IMPORTANT: give size
//            Scene scene = new Scene(root, 1000, 700);
//
//            stage.setScene(scene);
//            stage.centerOnScreen();   // center properly
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @FXML
    private void openVoterLogin(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/onlinevotingsystem/voter-login-view.fxml")
            );

            Parent root = loader.load();

            // get current stage
            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene().getWindow();

            // 🔥 IMPORTANT FIX
            stage.getScene().setRoot(root);

            // optional
            stage.centerOnScreen();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//    @FXML
//    private void openVoterLogin(ActionEvent event) {
//        try {
//            FXMLLoader loader = new FXMLLoader(
//                    getClass().getResource("/com/example/onlinevotingsystem/voter-login-view.fxml")
//            );
//
//            Parent root = loader.load();
//
//            // ✅ same stage, no popup
//            Stage stage = (Stage) ((Node) event.getSource())
//                    .getScene().getWindow();
//
//            stage.setScene(new Scene(root));
//            stage.setMaximized(true);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


}
