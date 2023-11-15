package com.roguelike_java;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.layout.Pane;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.control.Label;


import java.io.IOException;

import com.roguelike_java.Entities.Entity;

/**
 * JavaFX App
 */
public class App extends Application {

    static Scene scene; // Scene de base accessible par tt les classes
    private static Pane root;
    private EventHandler eventHandler;
    private Entity boris;
    
    public static final int sizeX = 60;
    public static final int sizeY = 40;

    //Images de fond :
    private static Image background = new Image("FondMoche.png");
    private static ImageView spriteBackground = new ImageView(background);

    private static Image backgroundText = new Image("FondText.png");
    private static ImageView spriteBackgroundText = new ImageView(backgroundText);

    @Override
    public void start(Stage stage) throws IOException {
        this.root = new Pane();
        this.scene = new Scene(root, (sizeX * Grid.sizeSprite) + (280), sizeY * Grid.sizeSprite);

        eventHandler = new EventHandler(boris);
        eventHandler.pollEvents(scene);

        stage.setScene(scene);
        stage.show();

        // INITIALISATION : //
        //Background
        root.getChildren().add(spriteBackground);
        spriteBackground.toBack();

        root.getChildren().add(spriteBackgroundText);
        spriteBackgroundText.setTranslateX(sizeX * Grid.sizeSprite);
        spriteBackgroundText.toBack();

        //Grid :
        Grid grid = new Grid(sizeX, sizeY);

        //Popup :
        PopupMouse.initPopup();
    }
    public static void main(String[] args) {
        launch();
    }

    //Methodes d'affichage de sprites/objets :
    public static void displaySprite(Entity entity){
        root.getChildren().add(entity.sprite);
    }
    public static void displaySprite(ImageView sprite){
        root.getChildren().add(sprite);
    }
    public static void displayText(Label label){
        root.getChildren().add(label);
    }

    //Supprime l'affichage d'un sprite a partir d'une entity donnée (d'un sprite déjà affiché)
    public static void deleteSprite(Entity entity){
        root.getChildren().remove(entity.sprite);
    }
    public static void deleteSprite(ImageView sprite){
        root.getChildren().remove(sprite);
    }

    //Remet le background en fond
    public static void backgroundUpdate(){
        spriteBackground.toBack();
    }

}
