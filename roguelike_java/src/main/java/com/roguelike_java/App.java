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
    private static Pane root;
    

    @Override
    public void start(Stage stage) throws IOException {
        this.root = new Pane();
        this.scene = new Scene(root, 1280, 720);

        stage.setScene(scene);
        stage.show();

        Grid grid = new Grid(20, 10);
    }

    public static void main(String[] args) {
        launch();
    }

    //Affiche une entity donnée.
    public static void displaySprite(Entity entity){
        root.getChildren().add(entity.sprite);

    }

    //Supprime l'affichage d'un sprite a partir d'une entity donnée (d'un sprite déjà affiché)
    public static void deleteSprite(Entity entity){
        root.getChildren().remove(entity.sprite);
    }

}