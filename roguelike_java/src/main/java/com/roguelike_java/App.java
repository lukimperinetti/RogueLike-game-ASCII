package com.roguelike_java;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private Pane root;
    private EventHandler eventHandler;

    @Override
    public void start(Stage stage) throws IOException {
        this.root = new Pane();
        this.scene = new Scene(root, 1280, 720);
        eventHandler = new EventHandler();
        eventHandler.pollEvents(scene);


        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}