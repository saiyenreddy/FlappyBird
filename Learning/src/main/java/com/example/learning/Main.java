package com.example.learning;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        new Game();

    }
    public static void main(String[] args) {
        Application.launch(args);
    }





}

