package com.example.onlinevotingsystem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/com/example/onlinevotingsystem/login-view.fxml")
        );

        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Online Voting System");
        stage.setScene(scene);

        // 🔥 FIXES YOUR PROBLEM
        stage.setMaximized(true);   // full screen
        stage.centerOnScreen();     // center

        stage.show();
    }
}
