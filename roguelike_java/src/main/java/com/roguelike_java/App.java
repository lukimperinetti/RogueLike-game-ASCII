package com.roguelike_java;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.layout.Pane;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    static Scene scene; // Scene de base accessible par tt les classes
    private static Pane root;
    private EventHandler eventHandler;
    private Entity boris;
    
    private int sizeX = 60;
    private int sizeY = 40;

    private static Image background = new Image("FondMoche.png");
    private static ImageView spriteBackground = new ImageView(background);

    @Override
    public void start(Stage stage) throws IOException {
        this.root = new Pane();
        this.scene = new Scene(root, sizeX * Grid.sizeSprite, sizeY * Grid.sizeSprite);
        eventHandler = new EventHandler(boris);
        eventHandler.pollEvents(scene);
    
        eventHandler = new EventHandler(boris);
        eventHandler.pollEvents(scene);

        stage.setScene(scene);
        stage.show();

        // ---- //
        root.getChildren().add(spriteBackground);
        spriteBackground.setTranslateZ(-10);

        Grid grid = new Grid(sizeX, sizeY);

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

    //Remet le background en fond
    public static void backgroundUpdate(){
        spriteBackground.toBack();
    }

}