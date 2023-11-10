package com.roguelike_java;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

//Classe de base de tt les entités présentes in game
public class Entity {
    
    private String name;
    private int ID;
    private static int globalID;

    private int coordX;
    private int coordY;

    //Visu :
    private Image image;
    public ImageView sprite;

    Entity(String name, int coordX, int coordY, String imageName){
        
        //ID :
        this.ID = globalID;
        globalID++;

        this.name = name;
        this.coordX = coordX;
        this.coordY = coordY;

        //Visu :
        image = new Image(imageName);
        sprite = new ImageView(image);   
    }

    public void move(int X, int Y){

        //Deplace le sprite
        sprite.setTranslateX(X);
        sprite.setTranslateY(Y);

        //Enregistre la nouvelle position
        this.coordX = X;
        this.coordY = Y;
    }


}
